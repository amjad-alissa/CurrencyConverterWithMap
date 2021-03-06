-------CMakeLists.txt-------
cmake_minimum_required(VERSION 3.3)

project(MyProjectName)

add_subdirectory(src)
add_subdirectory(test)

-------src/CMakeLists.txt--------
add_library(core ${core_SRCS})
add_executable(exe main.cpp)
target_link_libraries(exe core)

--------test/CMakeLists.txt----------
cmake_minimum_required(VERSION 3.3)

set(REPO ~/ClionProjects/.repo)

project(Test)

project(Example)

include(CTest)
enable_testing()

#set(gtest_disable_pthreads on) #needed in MinGW
include(${REPO}/DownloadProject/DownloadProject.cmake)
download_project(
        PROJ                googletest
        GIT_REPOSITORY      https://github.com/google/googletest.git
        GIT_TAG             master
        UPDATE_DISCONNECTED 1
        )

add_subdirectory(${googletest_SOURCE_DIR} ${googletest_BINARY_DIR} EXCLUDE_FROM_ALL)

#set(test_SRCS ADD ALL TEST SOURCE FILES HERE)
add_executable(runUnitTests gtest.cpp ${test_SRCS})
target_link_libraries(runUnitTests gtest gmock core)
#add_test(runUnitTests runUnitTests) #included in all tutorials but I don't know what it actually does.
