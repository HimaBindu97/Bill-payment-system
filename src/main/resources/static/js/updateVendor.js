/*  function init() {
//$(document).ready(function(){
	$(".vendor-form").hide();
	$(".vendor-details").hide();
} */
//});
function logout(){
    sessionStorage.clear();
    window.location="index.html";
}

function init() {
    if (sessionStorage.getItem("auth") != "yes") {
                
                window.location = 'index.html';
                alert("please login first.");
    }
    
}
function showUpdate() {
	

	var cid = $("#vendorIdQuery").val();

	$('#vendorId').empty();
	$('#vendorId').append(cid);
	
	
	var searchContollerUrl = "http://localhost:3100/Vendors/" + cid;
	$.ajax({
		type : "GET",
		url : searchContollerUrl,
		dataType : "json",
		async : false,

		success : function(response) {
			
			
			$(".vendor-search").hide();
			$(".vendor-form").show();
			$(".vendor-details").show();
			$("#vendorName").val(response.vendorName);
			$("#vendorCompanyRegistrationNo").val(
					response.vendorCompanyRegistrationNo);
			$("#vendorType").val(response.vendorType);
			$("#vendorAddress").val(response.vendorAddress);
			$("#vendorEmail").val(response.vendorEmail);
			$("#vendorContact").val(response.vendorContact);
			$("#vendorWebsite").val(response.vendorWebsite);
			$("#vendorCertificateIssuedDate").val(
					response.vendorCertificateIssuedDate.toString().substring(0,10));
			$("#vendorCertificateValidityDate").val(
					response.vendorCertificateValidityDate.toString().substring(0,10));
			$("#vendorEmployeesCount").val(response.vendorEmployeesCount);
			$("#vendorCustomerCount").val(response.vendorCustomerCount);
			$("#vendorYearOfEstablishment").val(
					response.vendorYearOfEstablishment);
			$("#vendorCountry").val(response.vendorCountry);
			$("#vendorState").val(response.vendorState);
		},
		
		error : function() {
			alert('VendorID is not registered ');
		}
	});
}

function getJson() {
	var $items = $('#vendorName, #vendorCompanyRegistrationNo, #vendorType, #vendorAddress, #vendorCountry, #vendorState, #vendorEmail, #vendorContact, #vendorWebsite, #vendorCertificateIssuedDate, #vendorCertificateValidityDate, #vendorEmployeesCount, #vendorCustomerCount, #vendorYearOfEstablishment');
	var obj = {};
	obj.vendorId=$("#vendorIdQuery").val();
	$items.each(function() {
		obj[this.id] = $(this).val();
	});
	var json = JSON.stringify(obj);
	return json;
}
function registerVendor() {
	var loginData = getJson();
	var vendorContollerUrl = "http://localhost:3100/Vendors/update/";
	$.ajax({
		type : "POST",
		async : false,
		url : vendorContollerUrl,
		data : loginData,
		contentType : "application/json",
		dataType : "json",
		success : function(response) {
			console.log(response);
			alert("Vendor Saved Succesfully");
			window.location = "/dashboard.html";
		},

		error : function() {
			alert('error');
		}

	});
}



/*
 * $(".id").hide(); $(".details").hide();
 */
jQuery(function($) {

	$.validator.addMethod("caps", function(value, element) {

		return this.optional(element)
				|| value == value.match(/^[A-Z][a-zA-Z\s]+$/);
	});
	
	$.validator.addMethod("alpha", function(value, element) {

		return this.optional(element) || value == value.match(/^[A-Za-z\s]+$/)
				&& value != value.match(/[\s]+/);

	});
	$.validator.addMethod("text", function(value, element) {

		return this.optional(element) || value != value.match(/[\s]+/);

	});
	$.validator.addMethod("countrydd", function(value) {
		if (value == "0") {
			return false;
		} else {
			return true;
		}
	});
	$.validator.addMethod("statedd", function(value) {
		if (value == "0") {
			return false;
		} else {
			return true;
		}
	});
	$.validator.addMethod("vendordd", function(value) {
		if (value == "0") {
			return false;
		} else {
			return true;
		}
	});

	$("#vendor-form").validate({

		rules : {
			name : {
				required : true,
				alpha : true,
				caps : true,
				text : true
			},
			regNumber : {
				required : true,
				digits : true,
				minlength : 5,
				maxlength : 5
			},
			address : {
				required : true,
				text : true
			},
			email : {
				required : true,
				email : true
			},
			country : {
				countrydd : true
			},
			state : {
				statedd : true
			},
			vendorType : {
				vendordd : true
			},
			website : {
				required : true,
				url : true
			},
			number : {
				required : true,
				digits : true,
				minlength : 10,
				maxlength : 10
			},
			empCount : {
				required : true

			},
			custCount : {
				required : true

			},
			estYear : {
				required : true

			},
		},
		messages : {
			name : {
				required : "Please enter your name",
				alpha : "Alphabets and spaces only allowed",
				caps : "Name should start with capital letter",
				text : "Please enter a valid name"
			},
			regNumber : {
				required : "Please enter your company registration number",
				digits : "Please enter valid number",
				minlength : "Please enter 5 digits",
				maxlength : "Only 5 digits allowed"
			},
			address : {
				required : "Please enter your address",
				text : "Please enter valid address"
			},
			email : {
				required : "Please enter an email address",
				email : "Please enter a valid email address"
			},
			country : {
				countrydd : "Please select one option"
			},
			state : {
				statedd : "Please select one option"
			},
			vendorType : {
				vendordd : "Select one option"
			},
			website : {
				required : "Please enter a website url",
				url : "Please enter valid url"
			},
			number : {
				required : "Please enter your number",
				digits : "Please enter valid number",
				minlength : "Please enter minimum 10 digits",
				maxlength : "Only 10 digits allowed"
			},
			empCount : {
				required : "Please enter your employee count"

			},
			custCount : {
				required : "Please enter your customer count"

			},
			estYear : {
				required : "Please enter the year established"

			},

		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});

