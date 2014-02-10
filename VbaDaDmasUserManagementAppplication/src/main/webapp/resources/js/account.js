$(document).ready(function() {
	bindLogin();
	function bindLogin() {
		$('.login-form').submit(function() {
			var username = $('.login-form').find('input[name="username"]');
			var password = $('.login-form').find('input[name="password"]');
			if ($.trim(username.val()) == '') {
				username.focus();
				alert('Please Enter Username');
				return;
			}
			if ($.trim(password.val()) == '') {
				password.focus();
				alert('Please Enter Password');
				return;
			}
			$.ajax({
				type : "post",
				url : appContextPath + "/system/checklogin.do",
				data : {
					type : 'creator',
					username : username.val(),
					password : password.val(),
				},
				async : true,
				dataType : "json",
				success : function(ret) {
					if (ret) {
						if (ret.code == "ok") {
							window.location.href = appContextPath + '/user/list.do';
						} else if (ret.code == 'unvalid_user') {
							alert('Invalid Username or Password');
							username.focus();
						} else if (ret.code == 'inactive') {
							alert('You are inactive,please contact adminstrator');
						}
					} else {
						alert('Login Failed');
					}
				},
				error : function() {
					alert('Login Failed');
				}
			});
			return false;
		});
	}
	
	$('#passwordInput, #confirmPasswordInput').on('keyup', function(e) {
		var pass= $('#passwordInput').val();
		var confpass= $('#confirmPasswordInput').val();
		 alert (pass+confpass);
	if($('#passwordInput').val() != '' && $('#confirmPasswordInput').val() != '' && $('#passwordInput').val() != $('#confirmPasswordInput').val())
	{
	$('#passwordStrength').removeClass().addClass('alert alert-error').html('Passwords do not match!');
	 
	return false;
	}

	// Must have capital letter, numbers and lowercase letters
	var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
	 
	// Must have either capitals and lowercase letters or lowercase and numbers
	var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	 
	// Must be at least 6 characters long
	var okRegex = new RegExp("(?=.{6,}).*", "g");
	 
	if (okRegex.test($(this).val()) === false) {
	// If ok regex doesn't match the password
	$('#passwordStrength').removeClass().addClass('alert alert-error').html('Password must be 6 characters long.');
	 
	} else if (strongRegex.test($(this).val())) {
	// If reg ex matches strong password
	$('#passwordStrength').removeClass().addClass('alert alert-success').html('Good Password!');
	} else if (mediumRegex.test($(this).val())) {
	// If medium password matches the reg ex
	$('#passwordStrength').removeClass().addClass('alert alert-info').html('Make your password stronger with more capital letters, more numbers and special characters!');
	} else {
	// If password is ok
	$('#passwordStrength').removeClass().addClass('alert alert-error').html('Weak Password, try using numbers and capital letters.');
	}
	return true;
	});
	 
});