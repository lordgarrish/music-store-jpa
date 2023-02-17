# music-store-jpa
### Description
Music store website made with JavaEE, Servlets, JSP and JPA (Hibernate).

The Music Store website is a website for a fictional record company "Music Against Humanity". Although this isn't a real website, it illustrates
the skills that I've learned during studying Java Servlets and JSP API, that can be helpful for developing a real site.

Music Store website consists of 4 main sections: Home page, Catalog, News (work in progress) and Cart.<br>
<br>
<b>Home page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/6a/_2314e5814cdf1db021ea37eb88c5f06a.jpeg)](https://fastpic.org/view/115/2021/1014/_2314e5814cdf1db021ea37eb88c5f06a.png.html)
<br><br>
<b>Catalog page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/87/_36fa36c13de1dde1f18273baa7383387.jpeg)](https://fastpic.org/view/115/2021/1014/_36fa36c13de1dde1f18273baa7383387.png.html)

While browsing store catalog, user can view music cd by clicking on its titles. This redirects user to album description page.<br>

<b>Product description page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/5d/_8689fa28d0eedf4adb8c2a5ae63c5d5d.jpeg)](https://fastpic.org/view/115/2021/1014/_8689fa28d0eedf4adb8c2a5ae63c5d5d.png.html)

Users can add and/or remove individual products from the cart. Cart page shows all products in cart and total price.<br>

<b>Cart page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/e8/_3cba5d493ce4cb21bbc8a912b5926be8.jpeg)](https://fastpic.org/view/115/2021/1014/_3cba5d493ce4cb21bbc8a912b5926be8.png.html)

After selecting products and putting them in cart, user then goes to checkout page to enter his personal info and shipping address.<br>

<b>Checkout page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/83/_05ce0a1d0d445515d924d8c1fb3fe583.jpeg)](https://fastpic.org/view/115/2021/1014/_05ce0a1d0d445515d924d8c1fb3fe583.png.html)

Finally, after filling his shipping address and credit card info, user gets to page with all information about his order<br>

<b>Order information page</b><br>
[![FastPic.Ru](https://i115.fastpic.org/thumb/2021/1014/88/_831ad412403ed6910b04a7a7f014ff88.jpeg)](https://fastpic.org/view/115/2021/1014/_831ad412403ed6910b04a7a7f014ff88.png.html)
<br>

<b>Database schema</b><br>
[![FastPic.Ru](https://i116.fastpic.org/thumb/2022/0215/81/cc7902d03141305980b5e565d44a8e81.jpeg)](https://fastpic.org/view/116/2022/0215/cc7902d03141305980b5e565d44a8e81.png.html)
### Build and usage
Build project using Maven:
```
mvn clean package
```
Deploying the WAR to Tomcat:
1. Copy WAR file from `target/music-store-jpa-0.0.1-SNAPSHOT.war` to the `tomcat/webapps/` folder.
2. From a terminal, navigate to the `tomcat/bin` folder and execute:
- on Windows
```
catalina.bat run
```
- on Unix-based systems
```
catalina.sh run
```
3. Go to http://localhost:8080/
