@REM
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM   http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM

@REM Requires JAVA JDK 1.6+
@REM If you plan to use the UMLS Resources, set/export env variables
@REM set ctakes.umlsuser=[username], ctakes.umlspw=[password]
@REM or add the properties
@REM -Dctakes.umlsuser=[username] -Dctakes.umlspw=[password]

@REM Guess CTAKES_HOME if not defined
SETLOCAL
set CURRENT_DIR=%cd%
if not "%CTAKES_HOME%" == "" goto gotHome
set CTAKES_HOME=%CURRENT_DIR%
if exist "%CTAKES_HOME%\bin\runctakesCPE.bat" goto okHome
cd ..
set CTAKES_HOME=%cd%

:gotHome
if exist "%CTAKES_HOME%\bin\runctakesCPE.bat" goto okHome
echo The CTAKES_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end

:okHome
cd %CTAKES_HOME%
java -cp "%CTAKES_HOME%/lib/*;%CTAKES_HOME%/desc/;%CTAKES_HOME%/resources/" -Dlog4j.configuration=file:/%CTAKES_HOME%/config/log4j.xml -Xms512M -Xmx1024M org.apache.uima.tools.cpm.CpmFrame

:end
ENDLOCAL
