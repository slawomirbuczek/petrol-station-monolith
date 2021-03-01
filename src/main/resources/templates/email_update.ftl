<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <style type="text/css">
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>

</head>

<body style="margin: 0; padding: 0;">

<p>Hello there,</p>
<p>You are receiving this email beacause we received a email change request for your account.</p>
<p>Your token: ${token}</p>
<p>If you did not request a email change, please change your password immediately.</p>

<p>Regards,</p>
<p>Petrol Station Team</p>

</body>

</html>
