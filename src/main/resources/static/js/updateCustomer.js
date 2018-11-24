
	$(".customer-form").hide();


function showUpdate() {
	
	/*$("#cSearch").hide();
	$(".customer-form").show();
	$(".customer-details").show(); */


	var cid=$("#customerIdQuery").val();
	$('#customerId').empty();
	$('#customerId').append(cid);
	var searchContollerUrl = "http://localhost:3100/customers/"+cid;
	$.ajax({
		type : "GET",
		url : searchContollerUrl,
		dataType : "json",
		async : false,
		success : function(response) {
			
			// $("#uName").val(response.customerName);
			
			
			
			$("#customerName").val(response.customerName);
			$("#customerAddress").val(response.customerAddress);
			$("#customerContactNumber").val(response.customerContactNumber);
			$("#customerMailId").val(response.customerMailId);
			$("#customerCountry").val(response.customerCountry);
			$("#customerState").val(response.customerState);
			$("#customerIdentificationdocument").val(response.customerIdentificationdocument);
			$("#customerDocumentDetailNumber").val(response.customerDocumentDetailNumber);
			$("#customerRegistrationDate").val(response.customerRegistrationDate);
			$("#vendorType").val(response.vendorType);
			$("#customerCardNumber").val(response.customerCardNumber);
			$("#customerBalance").val(response.customerBalance);
			
		},
		
		error : function(){
			alert('error');
		}
	});
}

function getFalse(){
	return false;
}
function getJson() {
	var $items = $('#customerName,  #customerAddress, #customerContactNumber, #customerMailId, #customerCountry, #customerState, #customerIdentity, #customerIdentificationdocument,  #customerDocumentDetailNumber,#customerRegistrationDate, #vendorType, #customerCardNumber, #customerBalance');
	
	var obj = {};
	obj.customerId=$("#customerIdQuery").val();
	$items.each(function() {
		obj[this.id] = $(this).val();
	});
	var json = JSON.stringify(obj);
	
	return json;
}

function logout(){
    sessionStorage.clear();
    window.location="index.html";
}

function init() {
    if (sessionStorage.getItem("auth") != "yes") {
    	 alert("please login first.");
                window.location = 'index.html';
    }
    
}
function registerCustomer() {
	var loginData = getJson();
	var customerContollerUrl = "http://localhost:3100/customers/update/";
	$.ajax({
		type : "POST",
		async : false,
		url : customerContollerUrl,
		data : loginData,
		contentType : "application/json",
		dataType : "json",
		success : function(response) {

			console.log(response);
			alert('Customer Saved Successfully');
			// window.location = "/dashboard.html";
		},

		error : function() {
			alert('error2');
		}

	});
	
	jQuery(function($) {
		    var dropvar;
		    //for name validation
		    $.validator.addMethod("alpha", function(value, element){

		        return this.optional(element) || value == value.match(/^[a-zA-Z\s]+$/) && value != value.match(/[\s]+/) ;

		    });
		    $.validator.addMethod("text", function(value, element){

		        return this.optional(element) || value != value.match(/[\s]+/) ;

		    });
		    $.validator.addMethod("countrydd", function(value){
		        if (value == "0") {
		            return false;
		        }
		        else    {
		            return true;
		        }
		    });
		    $.validator.addMethod("statedd", function(value){
		        if (value == "0") {
		            return false;
		        }
		        else    {
		            return true;
		        }
		    });
		    $.validator.addMethod("identity", function(value){
		        // if (value == "0") {
		        //     return false;

		        // }
		        if (dropvar == "1") {
		            return (value != value.match(/[\s]+/) && value == value.match(/^GMV[a-zA-Z0-9]+$/));
		        }

		        else if (dropvar == "2") {
		            return (value != value.match(/[\s]+/) && value == value.match(/^PASS[a-zA-Z0-9]+$/));
		        }

		        else if (dropvar == "3") {
		            return (value != value.match(/[\s]+/) && value == value.match(/^DL[a-zA-Z0-9]+$/));
		        }

		        else if (dropvar == "4") {
		            return (value != value.match(/[\s]+/) && value == value.match(/^PAN[a-zA-Z0-9]+$/));
		        }

		    });
		    $.validator.addMethod("dropdown", function(value){
		        dropvar = value;
		        if (dropvar == "0") {
		            return false;
		        }
		        else    {
		            return true;
		        }
		    });

		    $.validator.addMethod("vendordd", function(value){
		        if (value == "0") {
		            return false;
		        }
		        else    {
		            return true;
		        }
		    });

		    

		    $("#customer-form").validate({
		        
		    rules: {
		        name:  {
		            required: true,
		            alpha: true,
		            text: true

		        },
		        address: {
		            required: true,
		            text: true
		        },
		        email: {
		            required: true,
		            email: true
		        },
		        country: {
		            countrydd: true
		        },
		        state: {
		            statedd: true
		        },
		        number: {
		            required: true,
		            digits: true,
		            minlength: 10,
		            maxlength: 10
		        },
		        identity: {
		            dropdown: true
		        },
		        docdetailnumber: {
		            required: true,
		            identity: true
		        },
		        date: {
		            required: true,
		            date: true
		        },
		        vendorType: {
		            vendordd: true
		        },
		        cardNum: {
		            required: true,
		            digits: true,
		            minlength: 16,
		            maxlength: 16
		        },
		        balance: {
		            required: true,
		            digits: true
		        },
		        
		    },
		    messages: {
		        name: {
		            required: "Please enter your name",
		            alpha: "Alphabets and spaces only allowed",
		            text: "Please enter a valid name"
		        },
		        address: {
		            required: "Please enter your address",
		            text: "Please enter valid address"
		        },
		        email: {
		            required: "Please enter an email address",
		            email: "Please enter a valid email address"
		        },
		        country: {
		            countrydd: "Please select one option" 
		        },
		        state: {
		            statedd: "Please select one option" 
		        },
		        number: {
		            required: "Please enter your number",
		            digits: "Please enter valid number",
		            minlength: "Please enter minimum 10 digits",
		            maxlength: "Only 10 digits allowed"
		        }, 
		        identity: {
		             dropdown:  "Please select one option" 
		        },
		        docdetailnumber: {
		            required: "Please enter your ID",
		            identity: "Please enter Valid ID"
		        },
		        date: {
		            required: "Please select a date"
		        },
		        vendorType: {
		            vendordd: "Please select one option" 
		        },
		        cardNum: {
		            required: "Please enter card number",
		            digits: "Only numbers allowed",
		            minlength: "Please enter 16 digits",
		            maxlength: "Only 16 digits allowed"
		        },
		        balance: {
		            required: "Please enter balance",
		            digits: "Only numbers allowed"
		        },
		        
		    
		    },
		    submitHandler: function(form) {
		        form.submit();
		    }
		});
		});
}
