<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="style1.html"%>
<meta charset="ISO-8859-1">
<title>Quote Summary</title>
</head>
<body>

	<%@include file="header1.html"%>
	<div>
		<h3 align="center">Quote Summary</h3>
		<h2>
			<strong>Monthly Premium <font color="red">$</font>${currentQuote.monthlyPremium}
			</strong>
		</h2>
	</div>

	<form action="/HomeInsurance_Stephen_Ho/BuyQuote" method="post">
		<section>
			<input type="submit" value="Buy Quote" />
		</section>
	</form>
	<div>
		<section>
			<h3>Location Details</h3>
			<article>
				<table>
					<tr>
						<td><b><i>Quote Id</i></b></td>
						<td>${currentQuote.quoteId}</td>
					</tr>
					<tr>
						<td><b><i>Residence Type</i></b></td>
						<td>${currentLocation.residenceType}</td>
					</tr>
					<tr>
						<td><b><i>Address Line 1</i></b></td>
						<td>${currentLocation.addressLine1}</td>
					</tr>
					<tr>
						<td><b><i>Address Line 2</i></b></td>
						<td>${currentLocation.addressLine2}</td>
					</tr>
					<tr>
						<td><b><i>City</i></b></td>
						<td>${currentLocation.city}</td>
					</tr>
					<tr>
						<td><b><i>State</i></b></td>
						<td>${currentLocation.locationState}</td>
					</tr>
					<tr>
						<td><b><i>Zip</i></b></td>
						<td>${currentLocation.zipCode }</td>
					</tr>
					<tr>
						<td><b><i>Residence Use</i></b></td>
						<td>${currentLocation.residenceUse}</td>
					</tr>
				</table>
			</article>
			<article>
				<h3>Homeowner Details</h3>
				<table>
					<tr>
						<td><b><i>First Name</i></b></td>
						<td>${currentHomeowner.firstName}</td>
					</tr>
					<tr>
						<td><b><i>Last Name</i></b></td>
						<td>${currentHomeowner.lastName}</td>
					</tr>
					<tr>
						<td><b><i>Date of Birth</i></b></td>
						<td>${currentHomeowner.dob}</td>
					</tr>
					<tr>
						<td><b><i>Is Retired?</i></b></td>
						<td><c:choose>
								<c:when test="${currentHomeowner.retiredStatus=='1'}">
							Yes
							</c:when>
								<c:otherwise>No
							</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td><b><i>Social Security Number</i></b></td>
						<td>${currentHomeowner.ssn}</td>
					</tr>
					<tr>
						<td><b><i>Email Address</i></b></td>
						<td>${currentHomeowner.email}</td>
					</tr>
				</table>
			</article>
		</section>
		<section>
			<article>
				<h3>Property Details</h3>
				<table>
					<tr>
						<td><b><i>Market Value</i></b></td>
						<td>${currentProperty.marketValue}</td>
					</tr>
					<tr>
						<td><b><i>Year Built</i></b></td>
						<td>${currentProperty.yearBuilt}</td>
					</tr>
					<tr>
						<td><b><i>Square Footage</i></b></td>
						<td>${currentProperty.squareFootage}</td>
					</tr>
					<tr>
						<td><b><i>Dwelling Style</i></b></td>
						<td>${currentProperty.dwellingType}</td>
					</tr>
					<tr>
						<td><b><i>Roof Material</i></b></td>
						<td>${currentProperty.roofMaterial}</td>
					</tr>
					<tr>
						<td><b><i>Garage Type</i></b></td>
						<td>${currentProperty.garageType}</td>
					</tr>
					<tr>
						<td><b><i>Number of Full Baths</i></b></td>
						<td><c:choose>
								<c:when test="${currentProperty.fullBaths=='4'}">
							4 or more
							</c:when>
								<c:otherwise>${currentProperty.fullBaths}
							</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td><b><i>Number of Half Baths</i></b></td>
						<td><c:choose>
								<c:when test="${currentProperty.halfBaths=='4'}">
							4 or more
							</c:when>
								<c:otherwise>${currentProperty.halfBaths}
							</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td><b><i>Has Swimming Pool</i></b></td>
						<td><c:choose>
								<c:when test="${currentProperty.pool=='1'}">
							True
							</c:when>
								<c:otherwise>False
							</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
			</article>
			<article>
				<h3>Coverage Details</h3>
				<table>
					<tr>
						<td><b><i>Monthly Premium</i></b></td>
						<td>${currentQuote.monthlyPremium}</td>
					</tr>
					<tr>
						<td><b><i>Dwelling Coverage</i></b></td>
						<td>${currentQuote.dwellingCoverage}</td>
					</tr>
					<tr>
						<td><b><i>Detached Structures</i></b></td>
						<td>${currentQuote.detachedStructures}</td>
					</tr>
					<tr>
						<td><b><i>Personal Property</i></b></td>
						<td>${currentQuote.personalProperty}</td>
					</tr>
					<tr>
						<td><b><i>Additional Living Expenses</i></b></td>
						<td>${currentQuote.addLivingExp}</td>
					</tr>
					<tr>
						<td><b><i>Medical Expenses</i></b></td>
						<td>${currentQuote.medicalExpenses}</td>
					</tr>
					<tr>
						<td><b><i>Deductible</i></b></td>
						<td>${currentQuote.deductible}</td>
					</tr>
				</table>
			</article>
		</section>
	</div>
	<form action="/HomeInsurance_Stephen_Ho/BuyQuote" method="post">
		<section>
			<input type="submit" value="Buy Quote" />
		</section>
	</form>

</body>
</html>