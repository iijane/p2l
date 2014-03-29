<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href='assets/css/jquery-ui-1.10.4.custom.css' rel='stylesheet'
	type='text/css'>
<link href='assets/css/jquery.mentionsInput.css' rel='stylesheet'
	type='text/css'>
	<link href='assets/css/main.css' rel='stylesheet'
	type='text/css'>
<!-- style>
.textarea {
	font-family: arial;
	font-size: 12px;
	border: 0;
	width: 700px;
	height: 200px;
}

.realTextarea {
	margin: 0;
	background: transparent;
	position: absolute;
	z-index: 999;
}

.overlayTextarea {
	margin: 0;
	position: absolute;
	top: 1;
	left: 1;
	z-index: 998;
}

.textareaBorder {
	border: groove 1px #ccc;
	position: relative;
	width: 702px;
	height: 202px;
}

.highlight {
	background: yellow;
}
</style-->

<script type="text/javascript" src="assets/js/jquery-1.11.0.js"></script>
<script type="text/javascript"
	src="assets/js/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" src="assets/js/underscore.js"></script>
<script type="text/javascript" src="assets/js/jquery.elastic.js"></script>
<script type="text/javascript" src="assets/js/jquery.events.input.js"></script>
<script type="text/javascript" src="assets/js/jquery.mentionsInput.js"></script>
<script type="text/javascript" src="assets/js/post.js"></script>
</head>
<body>

	<form>
		Post:
		<textarea class="mention" id="post"></textarea>
		<!-- div id="myOtherTextarea" class="textarea overlayTextarea">
			<textarea class='mention caret'></textarea>
			</div-->
	</form>
</body>
</html>