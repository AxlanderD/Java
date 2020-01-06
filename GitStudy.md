## Git 常用命令
- git init:初始化仓库
- git clone url...:克隆到本地仓库
- git status:查看工作区和暂存区的状态
- git add 文件名:将修改的内容添加到暂存区
- git commit -m “commit messsage（附加的信息）” 文件名字:将暂存区的内容提交到本地库
- git log:查看log
- git pull < origin >< master >:<本地分支名>:下拉远程主机指定分支和本地指定分支合并
- git push < origin > <本地master>:<远程master>:推送到远程主机
### 分支命令
- git branch:查看分支
- git branch dev:创建dev分支
- git checkout dev:切换到dev分支
- git merge dev:合并分支
