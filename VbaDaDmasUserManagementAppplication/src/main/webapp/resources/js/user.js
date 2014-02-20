$(document).ready(function() {
	var teamId = $('[name=teamId]').bootstrapDualListbox();
	var overlay_opacity = 0.6;
	$('#btnCreateUserPage').click(function() {
		window.location.href = appContextPath + '/user/add.do';
	});
	function isValidEmail(email) {
		var filter = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		return filter.test(email);
	};
	function isValidePhone(phone) {
	    var filter = /^[0-9-+]+$/;
	    return filter.test(phone);
	};

	function checkForm(formId, pageType) {
		var formObj = $('#' + formId);
		if (!requiredField(formId, 'username', 'Please enter username')) {
			return false;
		}
		if (pageType == 'addUser') {
			if (!requiredField(formId, 'password', 'Please enter password')) {
				return false;
			}
		}
		if (!requiredField(formId, 'email', 'Please enter email')) {
			return false;
		}
		if (!isValidEmail(formObj.find('input[name="email"]').val())) {
			alert('Please enter valid email');
			formObj.find('input[name="email"]').focus();
			return false;
		}
		if (!isValidePhone(formObj.find('input[name="phone"]').val())) {
			alert('Please enter valid phone number');
			formObj.find('input[name="phone"]').focus();
			return false;
		}
		if (!requiredField(formId, 'firstName', 'Please enter first name')) {
			return false;
		}
		if (!requiredField(formId, 'lastName', 'Please enter last name')) {
			return false;
		}
		if (!requiredField(formId, 'phone', 'Please enter phone')) {
			return false;
		}
		return true;
	}
	function collectFormData(formId) {
//		var formObj = $('#' + formId);
		var fields = $('#' + formId + ' input,#' + formId + ' select,#' + formId + ' textarea');
		var data = {};
		
		fields.each(function(i) {
			console.log(i+"--"+$(this).attr('name'));
			data[$(this).attr('name')] = $(this).val();
		});
		alert( data.toSource() );
		return data;
	}
	function requiredField(formId, fieldName, tips) {
		var obj = $('#' + formId + ' input[name="' + fieldName + '"]');
		if (obj.val().trim() == '') {
			alert(tips);
			obj.focus();
			return false;
		}
		return true;
	}
	var sendingFlag = false;
	function postData(url, data, opts) {
		
	
		$.ajax({
			type : "post",
			url : url,
			data : data,
			async : true,
			dataType : "json",
			success : function(ret) {
				if (ret){
					if (ret.code == "ok") {
						alert('Submit successfully');
						if (opts && opts.success) {
							opts.success(ret);
						}
					}else if (ret.code == 'used') {
						alert(' UserName or Email has been associated to another user before');
					} else if (ret.code == 'fail') {
						alert('submit Failed');
					}	
				}
				
			},
			error : function() {
				alert('Submit Failed');
				if (opts && opts.error) {
					opts.error();
				}
			},
			complete : function(ret) {
				if (opts && opts.complete) {
					opts.complete(ret);
				}
			}
		});
	}

	function bindAddFormEvent() {
		$('#addUserForm').submit(function() {
			function saveUser() {
				if (sendingFlag == true) {
					console.log('sumbit locked');
					return;
				}
				if (!checkForm('addUserForm', 'addUser')) {
					return;
				}
				var data = collectFormData('addUserForm');
				sendingFlag = true;
				postData(appContextPath + "/user/save.do", data, {
					success : function() {
						window.location.href = appContextPath + "/user/list.do?r=" + (+new Date());
					},
					complete : function() {
						sendingFlag = false;
					}
				});
			}
			try {
				saveUser();
			} catch (e) {
				alert('system error');
			}
			return false;
		});
	}
	function bindEditFormEvent() {
		$('#editUserForm').submit(function() {
			function updateUser() {
				if (sendingFlag == true) {
					console.log('sumbit locked');
					return;
				}
				if (!checkForm('editUserForm')) {
					return;
				}
				var data = collectFormData('editUserForm');
				sendingFlag = true;
				postData(appContextPath + "/user/update.do", data, {
					success : function() {
						window.location.href = appContextPath + "/user/list.do?r=" + (+new Date());
					},
					complete : function() {
						sendingFlag = false;
					}
				});
			}
			try {
				updateUser();
			} catch (e) {
				alert('system error');
			}
			return false;
		});
	}
	(function() {
		$('#btnEditUserPage').click(function() {
			var obj = $('input[name="userId"]:checked');
			if (!obj || obj.length == 0) {
				alert('Please select a user to edit');
				return;
			}
			var userId = obj.val();
			window.location.href = appContextPath + '/user/edit.do?id=' + userId;
		});
	})();
	(function() {
		$('#btnUserDetailsPage').click(function() {
			var obj = $('input[name="userId"]:checked');
			if (!obj || obj.length == 0) {
				alert('Please select a user to edit');
				return;
			}
			var userId = obj.val();
			$.get(appContextPath + '/user/'+userId+'/details.do', function(data){
				if(data){
					$('#appAccessTable').find('tbody').empty();
					var tbodyAppAccess = $('#appAccessTable').find('tbody');
					$.each(data.userDetails.applicationAccess, function(key, value){
						tbodyAppAccess.append('<tr><td>'+key+'</td><td>'+value+'</td></tr>');
					});
					$('#teamAllocationTable').find('tbody').empty();
					var tbodyTealAllocation = $('#teamAllocationTable').find('tbody');
					$.each(data.userDetails.teams, function(i, item){
						tbodyTealAllocation.append('<tr><td>'+item+'</td></tr>');
					});					
				}
			});
			$('#userDetailPopup').modal();
		});
	})();	
	(function() {
		$('#btnChangePassword').click(function() {
			var obj = $('input[name="userId"]:checked');
			if (!obj || obj.length == 0) {
				alert('Please select a user to change password');
				return;
			}
			var tds = obj.parent().siblings();
			var username = tds.eq(0).text();
			var userId = obj.val();

			
			bindPopup($('#changePwdPopup').html(), {
				title : 'Change Password'
			},

			function() {
				var pwd = $('.ZebraDialog_Body input[name="password"]').val();
				var userId = $('.ZebraDialog_Body input[name="userId"]').val();
				savePopupData(appContextPath + "/user/change-password.do", {
					userId : userId,
					password : pwd
				});
			});
			
			
			var dialog = $('.ZebraDialog_Body');
			dialog.find('input[name="userId"]').val(userId);
			dialog.find('input[name="username"]').val(username);
	

			$('.ZebraDialog_Body input[name="password"],.ZebraDialog_Body input[name="confpassword"]').on('keyup', function PasswordAnalyzer(e) {
			if($('.ZebraDialog_Body input[name="password"]').val() != '' && $('.ZebraDialog_Body input[name="confpassword"]').val() !=
				'' && $('.ZebraDialog_Body input[name="password"]').val() != $('.ZebraDialog_Body input[name="confpassword"]').val())
			{

				$('.ZebraDialog_Body p ').text('Passwords do not match'  );	

			return false;
			}

			var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
			var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
			var okRegex = new RegExp("(?=.{6,}).*", "g");
			 
			if (okRegex.test($(this).val()) === false) {
			$('.ZebraDialog_Body p ').text('Password must be 6 characters long.'  ).addClass('text-danger');
			} else if (strongRegex.test($(this).val())) {
				$('.ZebraDialog_Body p ').text('Good Password!'  );	
			} else if (mediumRegex.test($(this).val())) {
				$('.ZebraDialog_Body p ').text('Make your password stronger with more capital letters, more numbers and special characters!'  );	
			} else {
				$('.ZebraDialog_Body p ').text('Weak Password, try using numbers and capital letters.'  );	
			}
	
			return true;
			});	
			
		});
	})();
	bindAddFormEvent();
	bindEditFormEvent();
	

});