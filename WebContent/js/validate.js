function validateInputEmail() {
	var inputEmail = document.getElementById("inputEmail").value;
	if (!(inputEmail.match("^[0-9]{6}$"))) {
		document.getElementById("register").disabled = true;
		document.getElementById("inputEmailValid").innerHTML = "Invalid Login Id";
	} else {
		document.getElementById("inputEmailValid").innerHTML = "";
		document.getElementById("register").disabled = false;
	}
	return true;
}

function validateInputEmail1() {
	var inputEmail = document.getElementById("inputEmail").value;
	if (!(inputEmail.match("^[0-9]{6}$"))) {
		document.getElementById("login").disabled = true;
		document.getElementById("inputEmailValid").innerHTML = "Invalid Login Id login id should be 6 digits long number";
	} else {
		document.getElementById("inputEmailValid").innerHTML = "";
		document.getElementById("login").disabled = false;
	}
	return true;
}

function validateInputField() {
	var inputNoOfVehicle = document.getElementById("inputField").value;
	if (!(inputNoOfVehicle.match("^[0-9]{1,}$"))) {
		document.getElementById("Approve").disabled = true;
		document.getElementById("Reject").disabled = true;
		document.getElementById("NoOfVehiclefield").innerHTML = "Sorry you can enter number only";
	} else {
		if (inputNoOfVehicle > 0) {
			document.getElementById("Approve").disabled = false;
			document.getElementById("Reject").disabled = true;
			document.getElementById("NoOfVehiclefield").style.display = 'none';
		} 
		else if (inputNoOfVehicle==0) {
			document.getElementById("Approve").disabled =true;
			document.getElementById("Reject").disabled = false;
			document.getElementById("NoOfVehiclefield").style.display = 'Enter positive number for Approval';
		}
		else {
			document.getElementById("Approve").disabled = true;
			document.getElementById("Reject").disabled = true;
			document.getElementById("NoOfVehiclefield").innerHTML = "Positive Number is required";
		}
	}
	return true;
}

function validatePassword() {
	var inputPassword = document.getElementById("inputPassword").value;		
	if (!(/^(?=.*\d)(?=.*\W)[0-9a-zA-Z\W\s]{8,20}$/.test(inputPassword))) {
		document.getElementById("register").disabled = true;
		document.getElementById("inputPasswordValid").innerHTML = "Sorry the Password did not match our requirements, Please try again with a valid password containing atleast a number and a special character and 8-20 character long";
	} else {
		document.getElementById("inputPasswordValid").innerHTML = "";
		document.getElementById("register").disabled = false;
	}
	return true;
}

function validateLoginId() {
	var x = document.forms["branch_Admin_home_form"]["userName"].value;
	if (!(x.match("/^[0-9]{6}$/"))) {
		/* document.forms["branch_Admin_home_form"]["userName"].value = ''; */
		alert("Invalid Login Id");
		document.forms["branch_Admin_home_form"]["userName"].focus();
	}
	return true;
}

function validatebranchLocation() {
	var inputLocation = document.getElementById("location").value;
	if (!(inputLocation.match("^[a-zA-Z]*$"))) {
		document.getElementById("Submit").disabled = true;
		document.getElementById("inputBranchLocationValid").style.display = 'inline';
		document.getElementById("inputBranchLocationValid").innerHTML = "Try using Alphabet";
	} else {
		document.getElementById("Submit").disabled = false;
		document.getElementById("inputBranchLocationValid").style.display = 'none';
	}
	return true;
}



function validateOccupation() {
	var inputOccupation = document.getElementById("Occupation").value;
	if (!(inputOccupation.match("^[a-zA-Z]*$"))) {
		document.getElementById("Save").disabled = true;
		document.getElementById("inputOccupationValid").style.display = 'inline';
		document.getElementById("inputOccupationValid").innerHTML = "Try using Alphabet";
	} else {
		document.getElementById("inputOccupationValid").style.display = 'none';
		document.getElementById("Save").disabled = false;
		
	}
	return true;
}

function nullCheck() {
	if(document.getElementById("inputusername").value == '' ||
	document.getElementById("customerName").value == ''||
	document.getElementById("datetimepicker").value == ''||
	document.getElementById("address").value == ''||
	document.getElementById("email").value == ''||
	document.getElementById("Mobile").value == ''||
	document.getElementById("Occupation").value == ''){
		return false;
	}
}

/*
 * function validateAddress() { var x =
 * document.forms["branch_Admin_home_form"]["Address"].value; if
 * (!(x.match("[^0-9 ]{1,}"))) {
 * document.forms["branch_Admin_home_form"]["Address"].value = ''; alert("Sorry
 * the address did not match our requirements, Please try again with a valid
 * address"); document.forms["branch_Admin_home_form"]["Address"].focus(); }
 * 
 * return true; }
 */

function validateEmail() {
	var email = document.getElementById("email").value;
	if (!(email.match("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
		document.getElementById("emailError").style.display = 'inline';
		document.getElementById("emailError").innerHTML = "Invalid E-mail";
		document.getElementById("Submit").disabled = true;
		return false;
	} else {
		document.getElementById("emailError").innerHTML = "";
		document.getElementById("emailError").style.display = 'none';
		document.getElementById("Submit").disabled = false;
		return true;
	}
	return true;
}

function validateUserPhoneNo() {
	var inputPhone = document.getElementById("Mobile").value;
	if (!(inputPhone.match("^[0-9]{3}[-]{1}[0-9]{7}$"))) {
		
		document.getElementById("MobileError").innerHTML = "Invalid Mobile no. (ex: 987-6543210)";
		document.getElementById("Save").disabled = true;

		return false;
	} else {
		document.getElementById("Save").disabled = false;
		document.getElementById("MobileError").innerHTML = "";
		document.getElementById("MobileError").style.display = 'none';
		return true;
	}

}

function validatePhoneNo() {
	var inputPhone = document.getElementById("inputPhone").value;
	if (!(inputPhone.match("^[0-9]{3}[-]{1}[0-9]{7}$"))) {
		document.getElementById("Submit").disabled = true;
		document.getElementById("inputPhoneValid").innerHTML = "Invalid Mobile no. (ex: 987-6543210)";
		return false;
	} else {
		document.getElementById("Submit").disabled = false;
		document.getElementById("inputPhoneValid").style.display = "none";
		return true;
	}

}
/*
 * function validateProfile(){ flagPhoneNo = }
 */
/*
 * function validatePhoneNo() { var x =
 * document.forms["branch_Admin_home_form"]["PhoneNo"].value; if
 * (!(x.match("[0-9]{3}[-]{1}[0-9]{7}"))) {
 * document.forms["branch_Admin_home_form"]["PhoneNo"].value = ''; alert("Sorry
 * the PhoneNo did not match our requirements, Please try again with a valid
 * Phone no with 3 digit std code followed by 7 digit phone no");
 * document.forms["branch_Admin_home_form"]["PhoneNo"].focus(); } return true; }
 */

function validateUserLoginId() {
	var x = document.forms["userHome Form"]["userid"].value;
	if (!(x.match("[0-9]{6}"))) {
		document.forms["userHome Form"]["userid"].value = '';
		alert("Invalid Login Id");
		document.forms["userHome Form"]["userid"].focus();
	}
	return true;
}

function validateCustomerNameR() {
	var inputPhone = document.getElementById("customerName").value;
	if (!(inputPhone.match("[^0-9 ]{1,}")) && !inputPhone) {
		document.getElementById("NameError").style.display = 'inline';
		document.getElementById("NameError").innerHTML = "You can't give a number here";
		document.getElementById("Save").disabled = true;
		return false;
	} else {
		document.getElementById("NameError").innerHTML = "";
		document.getElementById("NameError").style.display = 'none';
		document.getElementById("Save").disabled = false;
		return true;
	}
}

function validateCustomerName() {
	var inputPhone = document.getElementById("customerName").value;
	if (!(inputPhone.match("[^0-9 ]{1,}")) && !inputPhone) {
		document.getElementById("NameError").style.display = 'inline';
		document.getElementById("NameError").innerHTML = "You can't give a number here";
		document.getElementById("update").disabled = true;
		return false;
	} else {
		document.getElementById("NameError").innerHTML = "";
		document.getElementById("NameError").style.display = 'none';
		document.getElementById("update").disabled = false;
		return true;
	}
}

function validateUserEmail() {

	var email = document.getElementById("email").value;
	if (!(email.match("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
		document.getElementById("Save").disabled = true;
		document.getElementById("EmailError").innerHTML = "Invalid E-mail";
		
		return false;
	} else {
		document.getElementById("EmailError").innerHTML = "";
		document.getElementById("EmailError").style.display = 'none';
		document.getElementById("Save").disabled = false;
		return true;
	}
	return true;

}

function validatePrice() {
	var inputPhone = document.getElementById("Price").value;
	if (!(inputPhone.match("^[0-9]{1,}$"))) {
		document.getElementById("PriceError").style.display = 'inline';
		document.getElementById("PriceError").innerHTML = inputPhone + " "
				+ " not a number";
		document.getElementById("addVehicle").disabled = true;
		return false;
	} else {
		document.getElementById("PriceError").innerHTML = "";
		document.getElementById("PriceError").style.display = 'none';
		document.getElementById("addVehicle").disabled = false;
		return true;
	}
}

function validateMinPrice() {
	var MinPrice = document.getElementById("MinPrice").value;
	if (!(MinPrice.match("^[0-9]{1,}$"))) {
		document.getElementById("MinPriceError").style.display = 'inline';
		document.getElementById("MinPriceError").innerHTML = MinPrice + " "
				+ " not a number";
		document.getElementById("showVehicle").disabled = true;
		return false;
	} else {
		document.getElementById("MinPriceError").innerHTML = "";
		document.getElementById("MinPriceError").style.display = 'none';
		document.getElementById("showVehicle").disabled = false;
		return true;
	}
}

function validateMaxPrice() {
	var MaxPrice = document.getElementById("MaxPrice").value;
	var MinPrice = document.getElementById("MinPrice").value;
	if (!(MaxPrice.match("^[0-9]{1,}$"))
			&& (parseInt(MaxPrice) > parseInt(MinPrice))
			&& (document.getElementById("MinPrice").value != null)) {
		document.getElementById("MaxPriceError").style.display = 'inline';
		document.getElementById("MaxPriceError").innerHTML = MaxPrice + " "
				+ " not a number";
		document.getElementById("showVehicle").disabled = true;
		return false;
	} else {
		document.getElementById("MaxPriceError").innerHTML = "";
		document.getElementById("MaxPriceError").style.display = 'none';
		document.getElementById("showVehicle").disabled = false;
		return true;
	}
}

function validateRent() {
	var inputPhone = document.getElementById("rent").value;
	if (!(inputPhone.match("^[0-9]{1,}$"))) {
		document.getElementById("RentError").style.display = 'inline';
		document.getElementById("RentError").innerHTML = inputPhone + " "
				+ " not a number";
		document.getElementById("addVehicle").disabled = true;
		return false;
	} else {
		document.getElementById("RentError").innerHTML = "";
		document.getElementById("RentError").style.display = 'none';
		document.getElementById("addVehicle").disabled = false;
		return true;
	}
}

function validateVehicleno() {
	var inputPhone = document.getElementById("vehicleNo").value;
	if (!(inputPhone.match("^[0-9]{1,}$"))) {
		document.getElementById("VehiclenoError").style.display = 'inline';
		document.getElementById("VehiclenoError").innerHTML = inputPhone + " "
				+ " not a number";
		document.getElementById("addVehicle").disabled = true;
		return false;
	} else {
		document.getElementById("VehiclenoError").innerHTML = "";
		document.getElementById("VehiclenoError").style.display = 'none';
		document.getElementById("addVehicle").disabled = false;
		return true;
	}
}
/*
 * function validatePhoneNo() { var x = document.forms["userHome
 * form"]["mobile"].value; if (!(x.match("[0-9]{3}[-]{1}[0-9]{7}"))) {
 * document.forms["userHome form"]["mobile"].value = ''; alert("Sorry the
 * PhoneNo did not match our requirements, Please try again with a valid Phone
 * no with 3 digit std code followed by 7 digit phone no");
 * document.forms["userHome form"]["mobile"].focus(); } return true; }
 */

function editable() {
	document.getElementById("location").disabled = false;
	document.getElementById("address").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("inputPhone").disabled = false;
	document.getElementById("Submit").disabled = false;
	document.getElementById("Edit").disabled = true;
	document.getElementById("Submit").style.display='inline';
	document.getElementById("reset").style.display='inline';
	document.getElementById("Edit").style.display='none';
}

function edit() {
	document.getElementById("customerName").disabled = false;
	document.getElementById("Dob").disabled = false;
	document.getElementById("address").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("Mobile").disabled = false;
	document.getElementById("Occupation").disabled = false;
	document.getElementById("Save").style.display='inline';
	document.getElementById("reset").style.display='inline';
	document.getElementById("Save").disabled = false;
	document.getElementById("Edit").disabled = true;
	document.getElementById("Edit").style.display='none';
}

function submit(){
if($("#Dob").datepicker("getDate") === null) {
	  alert("no date selected");
	}
if($("#Dob").val() == "") {
	  alert("no date selected");
	}
}

function validateVehicleNo() {
	var vehicleNo = document.getElementById("vehicleNo").value;
	if (!(inputPhone.match("^[0-9]{1,}$"))) {
		document.getElementById("vehicleNoError").style.display = 'inline';
		document.getElementById("vehicleNoError").innerHTML = vehicleNo + " "
				+ " not a number";
		document.getElementById("Request").disabled = true;
		return false;
	} else {
		document.getElementById("vehicleNoError").innerHTML = "";
		document.getElementById("vehicleNoError").style.display = 'none';
		document.getElementById("Request").disabled = false;
		return true;
	}
}
