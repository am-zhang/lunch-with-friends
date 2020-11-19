<%@ page import = "csci201_finalProject.User, csci201_finalProject.Restaurant" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="style.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Commissioner:wght@300&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
	
	<title>Lunch With Friends</title>
	
	<style>
		h1 {
			text-align: center;
			color: white;
			font-size: 6em;
		}
		body {
			background-image: linear-gradient(rgba(0, 0, 0, 0.3), rgba(0, 0, 0, 0.3)),
			url('https://mir-s3-cdn-cf.behance.net/project_modules/1400_opt_1/dfee8745499369.583332846bf73.jpeg');
			background-repeat:no-repeat;
			background-position: center;
			background-size: cover;
			padding: 80px;
			padding-top: 110px;
		}
		html {
			height: 100%
		}
		.login button {
			position: fixed;
			top: 50px;
			right: 50px;
		}
		.searchbar {
			display: flex;
			justify-content: center;
		}
		p {
			color: white;
			text-align: center;
		}
		#description {
			position: fixed;
			left: 0;
			right: 0;
			bottom: 100px;
		}
	</style>
</head>
<body>
	<h1>Lunch With Friends</h1>
	<form class="login" action="restaurants.sjp"> <!-- replace this with redirect to login -->
		<button type="button">Log in with Google</button>
	</form>
	<form class="searchbar" action="restaurants.jsp"> <!-- replace this with a link to restaurants page -->
		<input type="text" placeholder="Search for your favorite restaurants...">
		<button type="submit"><i class="fa fa-search"></i></button>
		
	</form>
	
	<p id="description">A CSCI-201 project by Amanda Zhang, Amir Hegazy, Brenna Chen, Jaemyung Choi, and Soumika Guduru.</p>
</body>
</html>