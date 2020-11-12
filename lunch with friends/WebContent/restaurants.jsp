<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

	<link href="style.css" rel="stylesheet" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Commissioner:wght@300&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
	
	<title>Search Results</title>
	
	<style>
		#header {
			height: 15%;
			background-color: #f7b819;
			position: fixed;
			top: 0;
			left: 0;
			right: 0;
			z-index: -1;
			padding: 20px;
		}
		h1 {
			color: white;
			float: left;
		}
		.searchbar {
			position: fixed;
			top: 40px;
			left: 30%;
			width: 40%;
		}
		.searchbar button {
			background: white;
			color: #f7b819;
			width: 20%;
		}
		.searchbar button:hover {
			background: #f7b819;
			color: white;
		}
		#icons button {
			color: white;
			font-size: 2.5em;
			padding: 20px;
			float: right;
			background: none;
			border: none;
		}
		#map {
			position: fixed;
			bottom: 0;
			right: 0;
			width: 30%;
			height: 80%;
			background-color: #d4d4d4;
			z-index: -2;
		}
		#main {
			width: 70%;
			position: relative;
			top: 150px;
		}
	</style>
</head>
<body>
	<div id="header">
		<h1>Lunch With Friends</h1>
		<form class="searchbar" action=""> <!-- replace this with a link to restaurants page -->
			<input type="text" placeholder="Search for your favorite restaurants...">
			<button type="submit"><i class="fa fa-search"></i></button>
		</form>
		<div id="icons">
			<button type="button"><i class="far fa-user-circle"></i></button>
			<button type="button"><i class="far fa-bell"></i></button>
			<button type="button"><i class="far fa-comment-dots"></i></button>
		</div>
	</div>
	
	<div id="main">
		<p>Placeholder text for restaurants</p>
	</div>
	
	<div id="map"> <!-- map goes here -->
	</div>
</body>
</html>