<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin Index Page</title> <#include "../lib/header.ftl">
</head>
<body>
	<div class="container">
		<#include "../lib/logo-bar.ftl">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<ul class="nav nav-tabs">
						<li class="active"><a>User</a></li>
						<li><a href="${rc.contextPath}/app/list.do">Team & Application</a></li>
						<li><a href="${rc.contextPath}/report/list.do">Report</a></li>
					</ul>
				</div>
			</div>
			<div>
				<button id="btnCreateUserPage" class="btn btn-inverse" type="button">Create User</button>
				<button id="btnEditUserPage" class="btn btn-inverse" type="button">Edit User</button>
				<button id="btnUserDetailsPage" class="btn btn-inverse" type="button">User Details</button>
				<button id="btnChangePassword" class="btn btn-inverse" type="button">Change Password</button>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th>Username</th>
						<th>status</th>
						<th>FirstName</th>
						<th>LastName</th>						
					</tr>
				</thead>
				<tbody>
				
					<#if dataList?? && dataList?size &gt; 0>
						<#list dataList as item>
							<tr>
								<td><input type="radio" name="userId" value="${item.id}"></td>
								<td>${item.username}</td>
								<td>${item.status}</td>
								<td>${item.firstName!''}</td>
								<td>${item.lastName!''}</td>								
							</tr>
						</#list>
					<#else>
						<tr><td colspan="12" style="text-align: center;">no any record</td></tr>						
					</#if>
				</tbody>
			</table>
		</div>
	</div>

	<div id="changePwdPopup" class="hide">
		<form>
			<input type="hidden" name="userId" value=""> 
			<label style="width: 90px">username</label> 
			<input type="text" name="username" value="" disabled="disabled"/> 
			<label style="width: 90px">new password</label>
			<input type="password" name="password" value="" maxlength="30"/>
			<label style="width: 90px">confirm password</label>
			<input  type="password" name="confpassword" value="" maxlength="30"/>
			<p ></p>
		</form>
	</div>
	<div id="userDetailPopup" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header" style="background: none repeat scroll 0 0 #222222; color: #FFFFFF;">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="color: #FFFFFF;">×</button>
	    <h4 id="myModalLabel">User Details</h4>
	  </div>
	  <div class="modal-body">
	    <div class="row-fluid">
	    	<div class="span-6">
	    		<table id="appAccessTable" class="table table-bordered table-striped table-hover">
	    			<caption>Application Access</caption>
	    			<thead>
	    				<tr>
	    					<th>Application</th>
	    					<th>Access</th>
	    				</tr>
	    			</thead>
	    			<tbody>
	    			</tbody>
	    		</table>
	    	</div>
	    	<div class="span-6">
	    		<table id="teamAllocationTable" class="table table-bordered table-striped table-hover">
	    			<caption>Team Allocation</caption>
	    			<tbody>
	    			</tbody>
	    		</table>	    	
	    	</div>	    	
	    </div>
	  </div>
	  <div class="modal-footer">
	    <button class="btn btn-inverse" data-dismiss="modal" aria-hidden="true">Close</button>
	  </div>
	</div>	
	<script type="text/javascript" src="${rc.contextPath}/resources/js/user.js?v=${res_version}"></script>
	<#include "../lib/footer.ftl">
</body>
</html>