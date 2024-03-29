# AttendanceManagement

JavaEE期末大作业：[使用手册.pdf](https://github.com/canzhen/attendance-management-system/files/8704584/default.pdf)

<br/>

Server: Tomcat 8.0

Database: Mysql

字符集 UTF-8（Eclipse下配置请把所有默认字符集都改成UTF-8）

### 默认命名规则
首先大的命名规则是这样的，统一action命名为A_B，意思是，调用AAction.java类里的B方法。
例如，在地址栏里写http://localhost:8080/AttendanceManagement/login_index，则代表调用loginAction里的index方法。这样一来就要注意，action包里的类名的大小写就很重要，不要写错了。像例子里，是loginAction而不是LoginAction，虽然一般来说包名的开头应该是大写。
那么下面就从A_B的B开始说起——
默认index为首页，比如登录login功能， login_index.jsp则为登录的首页；register_index.jsp则为注册页面的首页（当然我们这里没有注册功能，只是举个例子）。
默认check为对于那些需要和数据库进行比对的页面的实际操作。
比如login这个功能，登录时需要查看数据库中是否有此人并且密码对不对，所以check方法就是和数据库进行比对的方法。在这里提一下，默认error为错误页面，比如登录login，login _error.jsp为登录错误页面。当然轻易不要用一个界面去显示错误信息，用javascript里的alert弹出对话框提示框，或者在页面上方显示一个小浮动条（是这么叫吗？我不太清楚23333）提示用户名密码错误即可，最后还是return INDEX，在index首页上进行判断即可。

### 注意事项
除了我写的方法，其他方法能private尽量private，使各个类之间降低耦合性，利于扩展。当然如果哪些东西我没考虑周全，要设成public也可以，但是要尽量少一点。

### 界面与功能
欢迎界面（welcome.jsp），一些简单的logo，界面里有一些简单的信息处理，比如若已登录则显示已登录，未登录则会有一个登录按钮或者href让用户登录。单击登录以后跳转到登录界面。
登录界面（login_index.jsp），老师或学生可以根据自己的学号、工号进行登录，初始密码默认为123456，action为Login.action，包含check()功能，与数据库进行比对；登录成功后则session.put("identity", "teacher")设置相应的identity
第一个是下拉框，第二三都是输入框。
登录成功之后，学生和老师分别进入到自己的首页，要有一个信息来判断是老师还是学生，分别进入到不同的主页。若是老师，则进入到teacher_index，若是学生，则进入到student_index。
学生点名，进入课程的页面，这个界面设置为首页（student_index.jsp），从后台数据库搜索获取当前课程并显示具体信息，包括任课老师、上课时间、已缺勤数包括是否超过缺勤数，若超过可以闪烁或者变红。还可以显示点名信息，是不是正在点名。若正在点名，并且老师已经上传照片，可以进行点名操作。
### 学生无课页（student_noCurrentClass.jsp）
是当前时间段内，学生没有课程。所以这里显示完该提示信息后，还需要把学生所有课的信息显示出来。这里需要从后台数据库中获取课程信息，显示在页面上，供学生选择。每一个课程的信息包括任课老师、上课时间、已缺勤数量，包括是否超过缺勤数，若超过可以闪烁或者变红。还可以在该界面显示图表，统计该学期总缺勤率，甚至可以按照等级记分，搞一个排行榜出来。迟到一次（拍照不显示，后来老师补上去的人就视为迟到）扣一分，缺课一次扣两分之类的，总分100，可以排名。当然这都是我的一些想法，你们可以根据实际情况自己拓展。课程若不在上课时间，可以设置成无法单击之类。
### 老师无课页（teacher_noCurrentClass.jsp）
是当前时间段内，老师没有课程。所以这里显示完该提示信息后，还需要把老师所有课的信息显示出来。这里同样也需要从数据库获取课程信息，显示在首页上，供老师选择。每一个课程信息包括上课时间，学生人数等。也可以有一些相应的图表，比如说缺勤率最低的课是什么课，满勤的课有哪些之类的，看着添加吧。课程若不在上课时间，可以设置成无法单击之类。



