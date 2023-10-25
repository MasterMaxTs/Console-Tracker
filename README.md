# job4j_tracker
#### ���������� ���������� "������� ������ - ������"
[![Build Status](https://app.travis-ci.com/MasterMaxTs/project_console-tracker.svg?branch=main)](https://app.travis-ci.com/MasterMaxTs/project_console-tracker)
[![codecov](https://codecov.io/gh/MasterMaxTs/job4j_tracker/branch/master/graph/badge.svg?token=MNF11YZPB1)](https://codecov.io/gh/MasterMaxTs/job4j_tracker)


![](https://img.shields.io/badge/java-11-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/maven-3.6.3-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/hibenate-5.6.10-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/docker-24.0.6-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/docker--compose-1.28.6-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/maven--checkstyle--plugin-3.1.1-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/maven--liquibase--plugin-3.10.3-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/maven--javadoc--plugin-3.2.0-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/maven--jacoco--plugin-0.8.6-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/maven--shade--plugin-3.2.4-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/lombok-1.18.24-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/slf4j--log4j12-2.0.5-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/DBMS:_PostgreSQL-14.0-4AB197)&nbsp;&nbsp;&nbsp;<br>
![](https://img.shields.io/badge/Test:_JUnit-4.12-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/Test:_Hamcrest-1.3-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/Test:_Mockito--core-4.6.1-4AB197)&nbsp;&nbsp;&nbsp;
![](https://img.shields.io/badge/Test:_h2database-2.1.214-4AB197)&nbsp;&nbsp;&nbsp;<br><br>
![](https://img.shields.io/badge/Package:-.jar-4AB197)&nbsp;&nbsp;&nbsp;


### ��� ������ �� ���������� ����������� ���������� "������� ������ - ������".

___
### ��������� ����������.

1. <b> � ���������� ������ ������ � �������� </b>


2. <b> ������ ����� ��������������: </b>
   
   - ���������� �����-������������� ID
   - ��������
   - ����� ��������
   - ��������


3. <b>������������ � ������� ���������� ������������ ���� � ������������� ���������:</b>

   - ���������� ������
   - ������ ������ �� ����� ������ �� ID
   - �������� ������ �� ID
   - ����������� ������ ���� ������
   - ����� ������ �� ID
   - ����� ������ ������ �� ���������� � ��������
   - ����� �� ����������


---
### ���� ����������

- Java 11
- Maven 3.6.3
- Hibernate-core v.5.6.10.Final
- Lombok v.1.18.24
- Slf4j-log4j12 v.2.0.5
- Docker v.24.0.6
- Docker Compose v.1.28.6
- ����: PostgreSQL v.14.0.
  <br><br>
- ������������:
    - JUnit v.4.12
    - Hamcrest v.1.3
    - Mockito-core v.4.6.1
    - ��: h2database v.2.1.214

<br>

- �������� �������: Java Archive (.jar)

---
### ���������� � ���������
- Java 11
- Maven v.3.6.3
- PostgreSQL v.14.0

<br>

---
### ������ �������

#### <ins>������������ ���������� � ������� Docker (�� Linux):</ins>

1. ���������, ���������� �� Docker:
    - ��������<br>
      ```docker --version```
    - ���� �� ����������, ����������


2. ���������, ���������� �� Docker Compose:
    - ��������<br>
      ```docker-compose --version```
    - ���� �� ����������, ����������


3. ������� ����� ������� � github �� ������ � ��������������� � ��������� ���������� �������:<br>
   [https://github.com/MasterMaxTs/project_console-tracker/archive](https://github.com/MasterMaxTs/project_console-tracker/archive/refs/heads/master.zip)


4. ������� ��������, ������� � ���������� �������:<br>
    - ��� <ins>�������</ins> ������������ � ������� ���������� ��������� ��������������� �������:
        - ```do�ker-compose build```
        - ```docker-compose up -d db```
        - ```docker-compose run job4j_tracker```

    - ��� <ins>������������</ins> ������� ���������� ��������� ��������������� �������:
        - ```docker-compose up -d db```
        - ```docker-compose run job4j_tracker```

<br>

#### <ins>������ ���������� ��������:</ins>
1. ���������� ���� PostgreSQL


2. ������� ���� ������ � ������ url_shortcuter:<br>
   ```create database tracker;```


3. ������� ����� ������� � github �� ������ � ��������������� � ��������� ����������:<br>
   [https://github.com/MasterMaxTs/project_console-tracker/archive](https://github.com/MasterMaxTs/project_console-tracker/archive/refs/heads/master.zip)


4. ������� � ���������� �������, ������� ��������� ������.</br>
- ��� <ins>�������</ins> ������� ���������� ��������� ��������������� �������:
    - ```mvn package -Pproduction -Dmaven.test.skip=true```
    - ```java -jar target/console_tracker.jar```

- ��� <ins>������������</ins> ������� ���������� ��������� �������:
    - ```java -jar target/console_tracker.jar```



---
### �������� �������

#### <ins>�������� � Docker:</ins>
- ����� �� ����������, ��������� ����, ����� ��������� ��������� �������:
    - ```docker-compose stop```

<br>

#### <ins>�������� ��������:</ins>
- �������� ���� ��������� ������

<br>

---
### �������������� � �����������
<br>

1. �������� ��� � ������� ��� ������� ����������:<br><br>
   ![img.png](img/start-app.JPG)



2. ��� ������� ���������� ��� �������� ������:<br><br>
   ![img.png](img/create-item.JPG)


3. ��� ������� ���������� ��� ����������� ���� ������:<br><br>
   ![img.png](img/show-all-items.JPG)


4. ��� ������� ���������� ��� �������� ��������� ������:<br><br>
   ![img.png](img/replace-item-success.JPG)


5. ��� ������� ���������� ��� ������� ��������� �� ������������ ������:<br><br>
   ![img.png](img/replace-item-error.JPG)


6. ��� ������� ���������� ��� �������� �������� ������:<br><br>
   ![img.png](img/delete-item-success.JPG)


7. ��� ������� ���������� ��� �������� �� ������������ ������:<br><br>
   ![img.png](img/delete-item-error.JPG)


8. ��� ������� ���������� ��� �������� ���������� ������ �� ID:<br><br>
   ![img.png](img/find-item-by-id-success.JPG)


9. ��� ������� ���������� ��� ������� ���������� �� ������������ ������:<br><br>
   ![img.png](img/find-item-by-id-error.JPG)


10. ��� ������� ���������� ��� �������� ���������� ������ ������ �� ����� � ��������:<br><br>
   ![img.png](img/find-item-by-key-in-name-success.JPG)


11. ��� ������� ���������� ��� ���������� ������ ������ �� ����� � ��������, ����� ����� �� ������ ������:<br><br>
   ![img.png](img/find-item-by-key-in-name-error.JPG)


12. ��� ������� ����������, ����� �������� ������������� ������ �� �������:<br><br>
   ![img.png](img/input-error.JPG)


13. ��� ������� ���������� ��� ���������� ����������:<br><br>
    ![img.png](img/exit-app.JPG)


---
### ����������� ����������� ���������� �������� �������

<br>

- � ������� ����������� ��� ���������� ��������� ������:

| �����                     | ��������                                    |
|:--------------------------|:--------------------------------------------|
  | . / trackers / MemTracker | ���������� � ������                         |
  | . / trackers / HbmTracker | ���������� � ��, ������ � ������� Hibernate |
  | . / trackers / SqlTracker | ���������� � ��, ������ � ������� Jdbc      |


<br>

- <b> � ������� ��������� ��� ������� ������:</b>

    - docker&emsp;&emsp;&emsp;: &emsp;������������ � ������� Docker Compose;
    - production&emsp;&nbsp;: &emsp;������ ���������� ��������;
    - test&emsp;&emsp;&emsp;&emsp;&nbsp;: &emsp;������������ ���������� � �������� ����������.


___
### ��������
* Email: java.dev-maxim.tsurkanov@yandex.ru
* Skype: https://join.skype.com/invite/ODADx0IJ3BBu
* VK: https://m.vk.com/id349328153
* Telegram: matsurkanov