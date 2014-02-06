<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Pet</title>
</head>
<body>
	<form method="post" action="./createpet" id="createpetform">
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Add a Pet</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Name</td>
						<td><input type="text" name="name" value="" /></td>
					</tr>
					<tr>
						<td>Tag Id</td>
						<td><input type="number" name="tag" value="" /></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><input type="text" name="gender" value="" /></td>
					</tr>
					<tr>
						<td>Breed</td>
						<td><input type="text" name="breed" value="" /></td>
					</tr>
					<tr>
						<td>Date of Birth</td>
						<td><input type="date" name="dob" value="" /></td>
					</tr>
					<tr>
						<td>Country</td>
						<td><input type="text" name="country" value="" /></td>
					</tr>
					<tr>
						<td>Description</td>
						<td>
							<textarea rows="4" cols="50" name="description" form="createpetform"/></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="reset" value="Reset" />
						<input type="submit" value="Submit" />
						</td>
					</tr>
					<tr>
						<td>
						</td>
					</tr>
				</tbody>
			</table>
	</form>
</body>
</html>