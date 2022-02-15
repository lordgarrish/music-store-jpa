package lordgarrish.servlet;

import lordgarrish.business.*;
import lordgarrish.dao.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    //This servlet does various actions like adding user's info and credit card to the database and creating
    //Order object and adding it to the database as well.

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = getServletContext();
        HttpSession session = req.getSession();

        String action = req.getParameter("action");
        if (action == null)
            action = "add";

        String url;
        if(action.equals("edit")) {
            url = "/checkout.jsp";
            sc.getRequestDispatcher(url).forward(req, resp);
        }
        else if(action.equals("add")) {
            url = "/confirm.jsp";
            Customer customer = createCustomer(req);
            session.setAttribute("customer", customer);
            sc.getRequestDispatcher(url).forward(req, resp);
        }
        else if(action.equals("confirm")) {
            url = "/payment.jsp";
            sc.getRequestDispatcher(url).forward(req, resp);
        }
        else if(action.equals("addCard")) {
            url = "/music_store_jpa_war_exploded/order";
            Customer customerWithCreditCard = addBillingDetails(req);
            session.setAttribute("customer", customerWithCreditCard);
            Order order = createOrder(customerWithCreditCard, session);
            session.setAttribute("order", order);

            //Saving customer and order in DB
            saveCustomer(customerWithCreditCard);
            saveOrder(order);

            resp.sendRedirect(url);
        }
    }

    private Customer createCustomer(HttpServletRequest req) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");
        Address userAddress = new Address(address, city, state, zip, country);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(userAddress);

        return customer;
    }

    private Customer addBillingDetails(HttpServletRequest req) throws ServletException {
        String creditCardNumber = req.getParameter("creditCardNumber");
        String creditCardExpirationDate = req.getParameter("creditCardExpirationDate");
        String cvv = req.getParameter("cvv");
        CreditCard card = new CreditCard(creditCardNumber, creditCardExpirationDate, cvv);

        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null) {
            throw new ServletException("Customer not found in current session");
        }

        customer.setCreditCard(card);
        return customer;
    }

    private Order createOrder(Customer customerWithCreditCard, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            System.err.println("Cart not found in current session");
            cart = new Cart();
        }

        String orderID = customerWithCreditCard.getLastName() + "_" + System.currentTimeMillis();
        Order order = (Order) session.getAttribute("order");
        if(order == null || order.isEmptyOrder()) {
            order = new Order(orderID, cart, customerWithCreditCard);
        }

        return order;
    }

    private void saveCustomer(Customer customer) throws ServletException {
        AbstractDao<Customer, String> customerDao = new CustomerDao();
        try {
            customerDao.save(customer);
        } catch (Exception e) {
            System.err.println("Can't save customer in database");
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void saveOrder(Order order) throws ServletException {
        AbstractDao<Order, String> orderDao = new OrderDao();
        try {
            orderDao.save(order);
        } catch (Exception e) {
            System.err.println("Can't save order in database");
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
