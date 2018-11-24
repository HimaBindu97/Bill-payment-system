var gData;

$(document).ready(function() {
       $(".image-form").hide();

       $(".payment-form").hide();
});
function logout(){
    sessionStorage.clear();
    window.location="index.html";
}

function init() {
    if (sessionStorage.getItem("auth") != "yes") {
                
                window.location = 'index.html';
              
    }
    
}
function Details() {

       $(".guide-line").hide();

       $(".query-form").hide();

       $(".image-form").show();

       $(".payment-form").show();

}

function populateVType() {
       

       $("#vendorType").empty();

       var str1 = $("#CustomerId").val();
       var url = "http://localhost:3100/customers/" + str1;

       $.ajax({
              type : "GET",
              url : url,
              dataType : "json",
              async: false,
              success : function(data) {
                     gData = data;
                     console.log(data);
                     var opt = '<option value="0" selected disabled>Select</option>';
                     $("#vendorType").append(opt);
                     for (i = 0; i < data.vendorType.length; i++) {
                           var opt = '<option value="' + data.vendorType[i].vendorType
                                         + '">' + data.vendorType[i].vendorType + '</option>';
                           $("#vendorType").append(opt);
                     }
              },
              error : function() {
                     alert("Customer Id not valid");
              }
       });
}

function checkCard(){
       
       var c1=gData.cardDetails.cardNumber;
       var c2=$("#cardNumber").val();
       if(c1!=c2){
    	   $("#cardNumber").val(null);
              alert("cardNumber invalid Sir");
       }
              
       
       
}
function saveCard(){
var cardData = getJsonCard();
var cardContollerUrl = "http://localhost:3100/customers/saveCard";
$.ajax({
       type : "POST",
       url : cardContollerUrl,
       data : cardData,
       async: false,
       contentType : "application/json",
       dataType : "json",
       success : function(response) {

              console.log(response);
              saveBill();
       },

       error : function() {
              alert("Payment could not be done");
       }

});

}
function getJsonCard(){
       var $items = $('#cardType, #cardValidity, #cvv, #cardNumber');
       
       var obj = {};
       obj.customerId=gData.customerId;
       $items.each(function() {
              obj[this.id] = $(this).val();
       });
       var json = JSON.stringify(obj);
       return json;
}
function saveBill(){
    
    var billData = getJsonBill();
    var billContollerUrl = "http://localhost:3100/billDetails/savebill";
    $.ajax({
           type : "POST",
           url : billContollerUrl,
           data : billData,
           async: false,
           contentType : "application/json",
           dataType : "json",
           success : function(response) {
        	   window.location = "dashboard.html";
                  alert("Payment done Successfully Sir");
                      
           },

           error : function() {
                  alert('gg');
           }

    });
    
}
function getJsonBill(){
    var $items = $('#vendorType, #vendorName, #amountPaid');
    
    var obj = {};
    obj.customerId=gData.customerId;
    $items.each(function() {
           obj[this.id] = $(this).val();
    });
    var json = JSON.stringify(obj);
    return json;
}



function populateNames() {

       $("#vendorName").empty();

       var str1 = $("#vendorType").val();
       var url = "http://localhost:3100/Vendors/" + str1 + "/names";

       $.ajax({
              type : "GET",
              url : url,
              dataType : "json",
              async: false,
              success : function(data) {
                     var opt = '<option value="0" disabled>Select</option>';
                     $("#vendorName").append(opt);
                     for (i = 0; i < data.length; i++) {

                           var opt = '<option value="' + data[i] + '">' + data[i]
                                         + '</option>';
                           $("#vendorName").append(opt);
                     }
              },
              error : function() {
                     alert("Vendor Type Could Not Be Fetched");
              }
       });
       
       getAmount();
}


function getAmount(){
       
       for(i=0;i<gData.vendorType.length;i++){
              if(gData.vendorType[i].vendorType==$('#vendorType').val()){
                     $('#amount').empty();
                     $('#amount').val(gData.vendorType[i].amount);
              }
       }
}

jQuery(function($) {

       $("#details-form").validate({

              rules : {

                     amount : {
                           required : true
                     },
              },
              messages : {

                     amount : {
                           required : "Please enter the bill amount"
                     },
              },

              submitHandler : function(form) {
                     form.submit();
              }
       });

       $("#payment-form").validate({
    	   
    	 
              rules : {
                     cardNum : {
                           required : true,
                           digits : true,
                           minlength : 16,
                           maxlength : 16

                     },

                     cvv : {
                           required : true,
                           digits : true,
                           minlength : 3,
                           maxlength : 3
                     },
                     validityMonth : {
                           required : true
                     },
              },
              messages : {
                     cardNum : {
                           required : "Please enter your number",
                           digits : "Please enter valid number",
                           minlength : "Please enter 16 digits",
                           maxlength : "Only 16 digits allowed"
                     },

                     cvv : {
                           required : "Please enter your number",
                           digits : "Please enter valid number",
                           minlength : "Please enter 3 digits",
                           maxlength : "Only 3 digits allowed"

                     },
                     validityMonth : {
                           required : "Please enter the validity month and year"
                     },
              },
              submitHandler : function(form) {
                     form.submit();
              }
       });
});
