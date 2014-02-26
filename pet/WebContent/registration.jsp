<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.google.gson.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="assets/css/jquery-ui-1.10.4.custom.css">
<script src="assets/js/jquery-1.11.0.js"></script>
<script src="assets/js/jquery-ui-1.10.4.custom.js"></script>
<%
	JsonObject inputJsonObject = (JsonObject) request
			.getAttribute("inputs");
%>
<script type="text/javascript">

	function genderInput(){

		var genderInputJS = "<%=inputJsonObject == null ? "" : inputJsonObject.get("genderInput").getAsString()%>";
		document.getElementById(genderInputJS).checked= true;
		
		var countriesInputJS = "<%=inputJsonObject == null ? "" : inputJsonObject.get("countriesInput").getAsString()%>";
		$('#countries').val(countriesInputJS);
		
		var countrycodeInputJS = "<%=inputJsonObject == null ? "" : inputJsonObject.get("countrycodeInput").getAsString()%>";
		$('#countrycode').val(countrycodeInputJS);
	
	};
		
	$(document).ready(function() {
		$('#postal').keydown(maskInputNumeric);
		$('#mobile').keydown(maskInputNumeric);
		$('#dob').datepicker();
		genderInput();
		
	});

	function maskInputNumeric() {
		if (event.shiftKey == true) {
			event.preventDefault();
		}

		if ((event.keyCode >= 48 && event.keyCode <= 57)
				|| (event.keyCode >= 96 && event.keyCode <= 105)
				|| event.keyCode == 8 || event.keyCode == 9
				|| event.keyCode == 37 || event.keyCode == 39
				|| event.keyCode == 46) {

		} else {
			event.preventDefault();
		}

		if ($(this).val().indexOf('.') !== -1 && event.keyCode == 190)
			event.preventDefault();
	};

	

</script>
</head>
<body>
	<form method="post" action="./registration">
		<center>
			<table border="1" width="30%" cellpadding="5">
				<thead>
					<tr>
						<th colspan="2">Enter Information Here</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>First Name</td>
						<td><input id="fname" type="text" name="fname" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("firstNameInput")
					.getAsString()%>'/></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input id="lname" type="text" name="lname" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("lastNameInput")
					.getAsString()%>' /></td>
					</tr>
					<tr>
						<td>Email <br />(Login User)
						</td>
						<td><input id="email" type="text" name="email" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("emailInput").getAsString()%>' /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input id="pass" type="password" name="pass" value="" /></td>
					</tr>
					<tr>
						<td>Re-Password</td>
						<td><input id="rpass" type="password" name="rpass" value="" /></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td><input id="M" type="radio" name="gender" value="M">Male 
						<input id="F" type="radio" name="gender" value="F">Female</td>
					</tr>
					<tr>
						<td>Date of Birth</td>
						<td><input id="dob" type="text" name="dob" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("dobInput").getAsString()%>' ></td>
					</tr>
					<tr>
						<td>Country</td>
						<td><select id="countries" name="countries" >
								<option value="Afghanistan">Afghanistan</option>
								<option value="Åland Islands">Åland Islands</option>
								<option value="Albania">Albania</option>
								<option value="Algeria">Algeria</option>
								<option value="American Samoa">American Samoa</option>
								<option value="Andorra">Andorra</option>
								<option value="Angola">Angola</option>
								<option value="Anguilla">Anguilla</option>
								<option value="Antarctica">Antarctica</option>
								<option value="Antigua and Barbuda">Antigua and Barbuda</option>
								<option value="Argentina">Argentina</option>
								<option value="Armenia">Armenia</option>
								<option value="Aruba">Aruba</option>
								<option value="Australia">Australia</option>
								<option value="Austria">Austria</option>
								<option value="Azerbaijan">Azerbaijan</option>
								<option value="Bahamas">Bahamas</option>
								<option value="Bahrain">Bahrain</option>
								<option value="Bangladesh">Bangladesh</option>
								<option value="Barbados">Barbados</option>
								<option value="Belarus">Belarus</option>
								<option value="Belgium">Belgium</option>
								<option value="Belize">Belize</option>
								<option value="Benin">Benin</option>
								<option value="Bermuda">Bermuda</option>
								<option value="Bhutan">Bhutan</option>
								<option value="Bolivia">Bolivia</option>
								<option value="Bosnia and Herzegovina">Bosnia and
									Herzegovina</option>
								<option value="Botswana">Botswana</option>
								<option value="Bouvet Island">Bouvet Island</option>
								<option value="Brazil">Brazil</option>
								<option value="British Indian Ocean Territory">British
									Indian Ocean Territory</option>
								<option value="Brunei Darussalam">Brunei Darussalam</option>
								<option value="Bulgaria">Bulgaria</option>
								<option value="Burkina Faso">Burkina Faso</option>
								<option value="Burundi">Burundi</option>
								<option value="Cambodia">Cambodia</option>
								<option value="Cameroon">Cameroon</option>
								<option value="Canada">Canada</option>
								<option value="Cape Verde">Cape Verde</option>
								<option value="Cayman Islands">Cayman Islands</option>
								<option value="Central African Republic">Central
									African Republic</option>
								<option value="Chad">Chad</option>
								<option value="Chile">Chile</option>
								<option value="China">China</option>
								<option value="Christmas Island">Christmas Island</option>
								<option value="Cocos (Keeling) Islands">Cocos (Keeling)
									Islands</option>
								<option value="Colombia">Colombia</option>
								<option value="Comoros">Comoros</option>
								<option value="Congo">Congo</option>
								<option value="Congo, The Democratic Republic of The">Congo,
									The Democratic Republic of The</option>
								<option value="Cook Islands">Cook Islands</option>
								<option value="Costa Rica">Costa Rica</option>
								<option value="Cote D'ivoire">Cote D'ivoire</option>
								<option value="Croatia">Croatia</option>
								<option value="Cuba">Cuba</option>
								<option value="Cyprus">Cyprus</option>
								<option value="Czech Republic">Czech Republic</option>
								<option value="Denmark">Denmark</option>
								<option value="Djibouti">Djibouti</option>
								<option value="Dominica">Dominica</option>
								<option value="Dominican Republic">Dominican Republic</option>
								<option value="Ecuador">Ecuador</option>
								<option value="Egypt">Egypt</option>
								<option value="El Salvador">El Salvador</option>
								<option value="Equatorial Guinea">Equatorial Guinea</option>
								<option value="Eritrea">Eritrea</option>
								<option value="Estonia">Estonia</option>
								<option value="Ethiopia">Ethiopia</option>
								<option value="Falkland Islands (Malvinas)">Falkland
									Islands (Malvinas)</option>
								<option value="Faroe Islands">Faroe Islands</option>
								<option value="Fiji">Fiji</option>
								<option value="Finland">Finland</option>
								<option value="France">France</option>
								<option value="French Guiana">French Guiana</option>
								<option value="French Polynesia">French Polynesia</option>
								<option value="French Southern Territories">French
									Southern Territories</option>
								<option value="Gabon">Gabon</option>
								<option value="Gambia">Gambia</option>
								<option value="Georgia">Georgia</option>
								<option value="Germany">Germany</option>
								<option value="Ghana">Ghana</option>
								<option value="Gibraltar">Gibraltar</option>
								<option value="Greece">Greece</option>
								<option value="Greenland">Greenland</option>
								<option value="Grenada">Grenada</option>
								<option value="Guadeloupe">Guadeloupe</option>
								<option value="Guam">Guam</option>
								<option value="Guatemala">Guatemala</option>
								<option value="Guernsey">Guernsey</option>
								<option value="Guinea">Guinea</option>
								<option value="Guinea-bissau">Guinea-bissau</option>
								<option value="Guyana">Guyana</option>
								<option value="Haiti">Haiti</option>
								<option value="Heard Island and Mcdonald Islands">Heard
									Island and Mcdonald Islands</option>
								<option value="Holy See (Vatican City State)">Holy See
									(Vatican City State)</option>
								<option value="Honduras">Honduras</option>
								<option value="Hong Kong">Hong Kong</option>
								<option value="Hungary">Hungary</option>
								<option value="Iceland">Iceland</option>
								<option value="India">India</option>
								<option value="Indonesia">Indonesia</option>
								<option value="Iran, Islamic Republic of">Iran, Islamic
									Republic of</option>
								<option value="Iraq">Iraq</option>
								<option value="Ireland">Ireland</option>
								<option value="Isle of Man">Isle of Man</option>
								<option value="Israel">Israel</option>
								<option value="Italy">Italy</option>
								<option value="Jamaica">Jamaica</option>
								<option value="Japan">Japan</option>
								<option value="Jersey">Jersey</option>
								<option value="Jordan">Jordan</option>
								<option value="Kazakhstan">Kazakhstan</option>
								<option value="Kenya">Kenya</option>
								<option value="Kiribati">Kiribati</option>
								<option value="Korea, Democratic People's Republic of">Korea,
									Democratic People's Republic of</option>
								<option value="Korea, Republic of">Korea, Republic of</option>
								<option value="Kuwait">Kuwait</option>
								<option value="Kyrgyzstan">Kyrgyzstan</option>
								<option value="Lao People's Democratic Republic">Lao
									People's Democratic Republic</option>
								<option value="Latvia">Latvia</option>
								<option value="Lebanon">Lebanon</option>
								<option value="Lesotho">Lesotho</option>
								<option value="Liberia">Liberia</option>
								<option value="Libyan Arab Jamahiriya">Libyan Arab
									Jamahiriya</option>
								<option value="Liechtenstein">Liechtenstein</option>
								<option value="Lithuania">Lithuania</option>
								<option value="Luxembourg">Luxembourg</option>
								<option value="Macao">Macao</option>
								<option value="Macedonia, The Former Yugoslav Republic of">Macedonia,
									The Former Yugoslav Republic of</option>
								<option value="Madagascar">Madagascar</option>
								<option value="Malawi">Malawi</option>
								<option value="Malaysia">Malaysia</option>
								<option value="Maldives">Maldives</option>
								<option value="Mali">Mali</option>
								<option value="Malta">Malta</option>
								<option value="Marshall Islands">Marshall Islands</option>
								<option value="Martinique">Martinique</option>
								<option value="Mauritania">Mauritania</option>
								<option value="Mauritius">Mauritius</option>
								<option value="Mayotte">Mayotte</option>
								<option value="Mexico">Mexico</option>
								<option value="Micronesia, Federated States of">Micronesia,
									Federated States of</option>
								<option value="Moldova, Republic of">Moldova, Republic
									of</option>
								<option value="Monaco">Monaco</option>
								<option value="Mongolia">Mongolia</option>
								<option value="Montenegro">Montenegro</option>
								<option value="Montserrat">Montserrat</option>
								<option value="Morocco">Morocco</option>
								<option value="Mozambique">Mozambique</option>
								<option value="Myanmar">Myanmar</option>
								<option value="Namibia">Namibia</option>
								<option value="Nauru">Nauru</option>
								<option value="Nepal">Nepal</option>
								<option value="Netherlands">Netherlands</option>
								<option value="Netherlands Antilles">Netherlands
									Antilles</option>
								<option value="New Caledonia">New Caledonia</option>
								<option value="New Zealand">New Zealand</option>
								<option value="Nicaragua">Nicaragua</option>
								<option value="Niger">Niger</option>
								<option value="Nigeria">Nigeria</option>
								<option value="Niue">Niue</option>
								<option value="Norfolk Island">Norfolk Island</option>
								<option value="Northern Mariana Islands">Northern
									Mariana Islands</option>
								<option value="Norway">Norway</option>
								<option value="Oman">Oman</option>
								<option value="Pakistan">Pakistan</option>
								<option value="Palau">Palau</option>
								<option value="Palestinian Territory, Occupied">Palestinian
									Territory, Occupied</option>
								<option value="Panama">Panama</option>
								<option value="Papua New Guinea">Papua New Guinea</option>
								<option value="Paraguay">Paraguay</option>
								<option value="Peru">Peru</option>
								<option value="Philippines">Philippines</option>
								<option value="Pitcairn">Pitcairn</option>
								<option value="Poland">Poland</option>
								<option value="Portugal">Portugal</option>
								<option value="Puerto Rico">Puerto Rico</option>
								<option value="Qatar">Qatar</option>
								<option value="Reunion">Reunion</option>
								<option value="Romania">Romania</option>
								<option value="Russian Federation">Russian Federation</option>
								<option value="Rwanda">Rwanda</option>
								<option value="Saint Helena">Saint Helena</option>
								<option value="Saint Kitts and Nevis">Saint Kitts and
									Nevis</option>
								<option value="Saint Lucia">Saint Lucia</option>
								<option value="Saint Pierre and Miquelon">Saint Pierre
									and Miquelon</option>
								<option value="Saint Vincent and The Grenadines">Saint
									Vincent and The Grenadines</option>
								<option value="Samoa">Samoa</option>
								<option value="San Marino">San Marino</option>
								<option value="Sao Tome and Principe">Sao Tome and
									Principe</option>
								<option value="Saudi Arabia">Saudi Arabia</option>
								<option value="Senegal">Senegal</option>
								<option value="Serbia">Serbia</option>
								<option value="Seychelles">Seychelles</option>
								<option value="Sierra Leone">Sierra Leone</option>
								<option value="Singapore">Singapore</option>
								<option value="Slovakia">Slovakia</option>
								<option value="Slovenia">Slovenia</option>
								<option value="Solomon Islands">Solomon Islands</option>
								<option value="Somalia">Somalia</option>
								<option value="South Africa">South Africa</option>
								<option value="South Georgia and The South Sandwich Islands">South
									Georgia and The South Sandwich Islands</option>
								<option value="Spain">Spain</option>
								<option value="Sri Lanka">Sri Lanka</option>
								<option value="Sudan">Sudan</option>
								<option value="Suriname">Suriname</option>
								<option value="Svalbard and Jan Mayen">Svalbard and Jan
									Mayen</option>
								<option value="Swaziland">Swaziland</option>
								<option value="Sweden">Sweden</option>
								<option value="Switzerland">Switzerland</option>
								<option value="Syrian Arab Republic">Syrian Arab
									Republic</option>
								<option value="Taiwan, Province of China">Taiwan,
									Province of China</option>
								<option value="Tajikistan">Tajikistan</option>
								<option value="Tanzania, United Republic of">Tanzania,
									United Republic of</option>
								<option value="Thailand">Thailand</option>
								<option value="Timor-leste">Timor-leste</option>
								<option value="Togo">Togo</option>
								<option value="Tokelau">Tokelau</option>
								<option value="Tonga">Tonga</option>
								<option value="Trinidad and Tobago">Trinidad and Tobago</option>
								<option value="Tunisia">Tunisia</option>
								<option value="Turkey">Turkey</option>
								<option value="Turkmenistan">Turkmenistan</option>
								<option value="Turks and Caicos Islands">Turks and
									Caicos Islands</option>
								<option value="Tuvalu">Tuvalu</option>
								<option value="Uganda">Uganda</option>
								<option value="Ukraine">Ukraine</option>
								<option value="United Arab Emirates">United Arab
									Emirates</option>
								<option value="United Kingdom">United Kingdom</option>
								<option value="United States">United States</option>
								<option value="United States Minor Outlying Islands">United
									States Minor Outlying Islands</option>
								<option value="Uruguay">Uruguay</option>
								<option value="Uzbekistan">Uzbekistan</option>
								<option value="Vanuatu">Vanuatu</option>
								<option value="Venezuela">Venezuela</option>
								<option value="Viet Nam">Viet Nam</option>
								<option value="Virgin Islands, British">Virgin Islands,
									British</option>
								<option value="Virgin Islands, U.S.">Virgin Islands,
									U.S.</option>
								<option value="Wallis and Futuna">Wallis and Futuna</option>
								<option value="Western Sahara">Western Sahara</option>
								<option value="Yemen">Yemen</option>
								<option value="Zambia">Zambia</option>
								<option value="Zimbabwe">Zimbabwe</option>
						</select></td>
					</tr>
					<tr>
						<td>State</td>
						<td><input id="state" type="text" name="state" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("stateInput").getAsString()%>' /></td>
					</tr>
					<tr>
						<td>City</td>
						<td><input id="city" type="text" name="city" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("cityInput").getAsString()%>' /></td>
					</tr>
					<tr>
						<td>Address</td>
						<td><input id="address" type="text" name="address" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("addressInput").getAsString()%>' /></td>
					</tr>
					<tr>
						<td>Postal Code</td>
						<td><input id="postal" type="text" name="postal" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("postalInput").getAsString()%>' /></td>
					</tr>
					<tr>
						<td>Mobile Number</td>
						<td><select id="countrycode" name="countrycode">
								<option value="213">Algeria (+213)
								<option value="376">Andorra (+376)
								<option value="244">Angola (+244)
								<option value="1264">Anguilla (+1264)
								<option value="1268">Antigua &amp; Barbuda (+1268)
								<option value="599">Antilles (Dutch) (+599)
								<option value="54">Argentina (+54)
								<option value="374">Armenia (+374)
								<option value="297">Aruba (+297)
								<option value="247">Ascension Island (+247)
								<option value="61">Australia (+61)
								<option value="43">Austria (+43)
								<option value="994">Azerbaijan (+994)
								<option value="1242">Bahamas (+1242)
								<option value="973">Bahrain (+973)
								<option value="880">Bangladesh (+880)
								<option value="1246">Barbados (+1246)
								<option value="375">Belarus (+375)
								<option value="32">Belgium (+32)
								<option value="501">Belize (+501)
								<option value="229">Benin (+229)
								<option value="1441">Bermuda (+1441)
								<option value="975">Bhutan (+975)
								<option value="591">Bolivia (+591)
								<option value="387">Bosnia Herzegovina (+387)
								<option value="267">Botswana (+267)
								<option value="55">Brazil (+55)
								<option value="673">Brunei (+673)
								<option value="359">Bulgaria (+359)
								<option value="226">Burkina Faso (+226)
								<option value="257">Burundi (+257)
								<option value="855">Cambodia (+855)
								<option value="237">Cameroon (+237)
								<option value="1">Canada (+1)
								<option value="238">Cape Verde Islands (+238)
								<option value="1345">Cayman Islands (+1345)
								<option value="236">Central African Republic (+236)
								<option value="56">Chile (+56)
								<option value="86">China (+86)
								<option value="57">Colombia (+57)
								<option value="269">Comoros (+269)
								<option value="242">Congo (+242)
								<option value="682">Cook Islands (+682)
								<option value="506">Costa Rica (+506)
								<option value="385">Croatia (+385)
								<option value="53">Cuba (+53)
								<option value="90392">Cyprus North (+90392)
								<option value="357">Cyprus South (+357)
								<option value="42">Czech Republic (+42)
								<option value="45">Denmark (+45)
								<option value="2463">Diego Garcia (+2463)
								<option value="253">Djibouti (+253)
								<option value="1809">Dominica (+1809)
								<option value="1809">Dominican Republic (+1809)
								<option value="593">Ecuador (+593)
								<option value="20">Egypt (+20)
								<option value="353">Eire (+353)
								<option value="503">El Salvador (+503)
								<option value="240">Equatorial Guinea (+240)
								<option value="291">Eritrea (+291)
								<option value="372">Estonia (+372)
								<option value="251">Ethiopia (+251)
								<option value="500">Falkland Islands (+500)
								<option value="298">Faroe Islands (+298)
								<option value="679">Fiji (+679)
								<option value="358">Finland (+358)
								<option value="33">France (+33)
								<option value="594">French Guiana (+594)
								<option value="689">French Polynesia (+689)
								<option value="241">Gabon (+241)
								<option value="220">Gambia (+220)
								<option value="7880">Georgia (+7880)
								<option value="49">Germany (+49)
								<option value="233">Ghana (+233)
								<option value="350">Gibraltar (+350)
								<option value="30">Greece (+30)
								<option value="299">Greenland (+299)
								<option value="1473">Grenada (+1473)
								<option value="590">Guadeloupe (+590)
								<option value="671">Guam (+671)
								<option value="502">Guatemala (+502)
								<option value="224">Guinea (+224)
								<option value="245">Guinea - Bissau (+245)
								<option value="592">Guyana (+592)
								<option value="509">Haiti (+509)
								<option value="504">Honduras (+504)
								<option value="852">Hong Kong (+852)
								<option value="36">Hungary (+36)
								<option value="354">Iceland (+354)
								<option value="91">India (+91)
								<option value="62">Indonesia (+62)
								<option value="98">Iran (+98)
								<option value="964">Iraq (+964)
								<option value="972">Israel (+972)
								<option value="39">Italy (+39)
								<option value="225">Ivory Coast (+225)
								<option value="1876">Jamaica (+1876)
								<option value="81">Japan (+81)
								<option value="962">Jordan (+962)
								<option value="7">Kazakhstan (+7)
								<option value="254">Kenya (+254)
								<option value="686">Kiribati (+686)
								<option value="850">Korea North (+850)
								<option value="82">Korea South (+82)
								<option value="965">Kuwait (+965)
								<option value="996">Kyrgyzstan (+996)
								<option value="856">Laos (+856)
								<option value="371">Latvia (+371)
								<option value="961">Lebanon (+961)
								<option value="266">Lesotho (+266)
								<option value="231">Liberia (+231)
								<option value="218">Libya (+218)
								<option value="417">Liechtenstein (+417)
								<option value="370">Lithuania (+370)
								<option value="352">Luxembourg (+352)
								<option value="853">Macao (+853)
								<option value="389">Macedonia (+389)
								<option value="261">Madagascar (+261)
								<option value="265">Malawi (+265)
								<option value="60">Malaysia (+60)
								<option value="960">Maldives (+960)
								<option value="223">Mali (+223)
								<option value="356">Malta (+356)
								<option value="692">Marshall Islands (+692)
								<option value="596">Martinique (+596)
								<option value="222">Mauritania (+222)
								<option value="269">Mayotte (+269)
								<option value="52">Mexico (+52)
								<option value="691">Micronesia (+691)
								<option value="373">Moldova (+373)
								<option value="377">Monaco (+377)
								<option value="976">Mongolia (+976)
								<option value="1664">Montserrat (+1664)
								<option value="212">Morocco (+212)
								<option value="258">Mozambique (+258)
								<option value="95">Myanmar (+95)
								<option value="264">Namibia (+264)
								<option value="674">Nauru (+674)
								<option value="977">Nepal (+977)
								<option value="31">Netherlands (+31)
								<option value="687">New Caledonia (+687)
								<option value="64">New Zealand (+64)
								<option value="505">Nicaragua (+505)
								<option value="227">Niger (+227)
								<option value="234">Nigeria (+234)
								<option value="683">Niue (+683)
								<option value="672">Norfolk Islands (+672)
								<option value="670">Northern Marianas (+670)
								<option value="47">Norway (+47)
								<option value="968">Oman (+968)
								<option value="680">Palau (+680)
								<option value="507">Panama (+507)
								<option value="675">Papua New Guinea (+675)
								<option value="595">Paraguay (+595)
								<option value="51">Peru (+51)
								<option value="63">Philippines (+63)
								<option value="48">Poland (+48)
								<option value="351">Portugal (+351)
								<option value="1787">Puerto Rico (+1787)
								<option value="974">Qatar (+974)
								<option value="262">Reunion (+262)
								<option value="40">Romania (+40)
								<option value="7">Russia (+7)
								<option value="250">Rwanda (+250)
								<option value="378">San Marino (+378)
								<option value="239">Sao Tome &amp; Principe (+239)
								<option value="966">Saudi Arabia (+966)
								<option value="221">Senegal (+221)
								<option value="381">Serbia (+381)
								<option value="248">Seychelles (+248)
								<option value="232">Sierra Leone (+232)
								<option value="65" >Singapore (+65)
								<option value="421">Slovak Republic (+421)
								<option value="386">Slovenia (+386)
								<option value="677">Solomon Islands (+677)
								<option value="252">Somalia (+252)
								<option value="27">South Africa (+27)
								<option value="34">Spain (+34)
								<option value="94">Sri Lanka (+94)
								<option value="290">St. Helena (+290)
								<option value="1869">St. Kitts (+1869)
								<option value="1758">St. Lucia (+1758)
								<option value="249">Sudan (+249)
								<option value="597">Suriname (+597)
								<option value="268">Swaziland (+268)
								<option value="46">Sweden (+46)
								<option value="41">Switzerland (+41)
								<option value="963">Syria (+963)
								<option value="886">Taiwan (+886)
								<option value="7">Tajikstan (+7)
								<option value="66">Thailand (+66)
								<option value="228">Togo (+228)
								<option value="676">Tonga (+676)
								<option value="1868">Trinidad &amp; Tobago (+1868)
								<option value="216">Tunisia (+216)
								<option value="90">Turkey (+90)
								<option value="7">Turkmenistan (+7)
								<option value="993">Turkmenistan (+993)
								<option value="1649">Turks &amp; Caicos Islands (+1649)
								<option value="688">Tuvalu (+688)
								<option value="256">Uganda (+256)
								<option value="44" >UK (+44)
								<option value="380">Ukraine (+380)
								<option value="971">United Arab Emirates (+971)
								<option value="598">Uruguay (+598)
								<option value="1">USA (+1)
								<option value="7">Uzbekistan (+7)
								<option value="678">Vanuatu (+678)
								<option value="379">Vatican City (+379)
								<option value="58">Venezuela (+58)
								<option value="84">Vietnam (+84)
								<option value="84">Virgin Islands - British (+1284)
								<option value="84">Virgin Islands - US (+1340)
								<option value="681">Wallis &amp; Futuna (+681)
								<option value="969">Yemen (North) (+969)
								<option value="967">Yemen (South) (+967)
								<option value="381">Yugoslavia (+381)
								<option value="243">Zaire (+243)
								<option value="260">Zambia (+260)
								<option value="263">Zimbabwe (+263)
						</select> <input id="mobile" type="text" name="mobile" value='<%=inputJsonObject == null ? "" : inputJsonObject.get("mobileInput").getAsString()%>' /></td>
					</tr>
					<tr>

						<td colspan="2"><input type="reset" value="Reset" /> <input
							type="submit" value="Submit" /></td>

					</tr>
					<tr>
						<td colspan="2">Already registered!! <a href="index.jsp">Login
								Here</a></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>