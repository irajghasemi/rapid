//Immediate function
(function () {
    "use strict";
     
    var restfulWebServiceURI, getUsers, ajaxCallFailed=null, colorRows, parseResponse=null;
     
    restfulWebServiceURI = "http://localhost:8080/guard_restfullayer/user/jsonp";
     
    // Execute after the DOM is fully loaded
    $(document).ready(function () {
        getUsers();
    });
 
    // Retrieve User List as JSONP
    getUsers = function () {
        $.ajax({
            cache: true,
            url: restfulWebServiceURI,
            data: "{}",
            type: "GET",
            jsonpCallback: "callback",
            contentType: "application/javascript",
            dataType: "jsonp",
            error: ajaxCallFailed,
            failure: ajaxCallFailed,
            success: parseResponse
        });         
    };
     
    // Called if ajax call fails
    ajaxCallFailed = function (jqXHR, textStatus) {
        console.log("Error: " + textStatus);
        console.log(jqXHR);
        $("#userList").empty();
        $("#userList").append("Error: " + textStatus).css("color", "red");;
    };
             
    // Called if ajax call is successful
    parseResponse = function (data) {
        var user = data.user;  
         
        var userList = "";
         
        userList = userList.concat("<div class='header'>" +
            "<span class='userId'>User Id</span>" +
            "<span class='names'>First and Last Name</span>" +
            "<span class='email'>Email</span>" +
            "<span class='password'>Password</span>" +
            "<span class='username'>Username</span>" +
            "<span class='address'>Address</span>" +
            "</div>");
         
        $.each(user, function(index, user) {
            userList = userList.concat("<div class='user'>" +
                "<span class='userId'>" +
                user.userid +
                "</span><span class='names'>" +
                user.firstname + " " + user.lastname +
                "</span><span class='email'>" +
                user.email +
                "</span><span class='password'>" +
                user.password +
                "</span><span class='username'>" +
                user.username +
                "</span><span class='address'>" +
                user.address.street +" "+user.address.zipcode+" "+user.address.city+
                "</span>  </div>");
        });
         
        $("#userList").empty();
        $("#userList").append(userList);
        colorRows();
    };
     
    // Styles the Employee List
    colorRows = function(){
        $("#userList .user:odd").addClass("odd");
        $("#userList .user:even").addClass("even");
        $("#userList .user:last").addClass("last");
    };
   
} ());