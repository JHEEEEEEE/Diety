# 🍏 **Diety** - 스마트한 다이어트 & 운동 관리 애플리케이션

**Diety**는 사용자가 운동 기록을 관리하고, 개인 프로필을 수정하며, 다이어트 목표를 달성할 수 있도록 돕는 스마트한 애플리케이션.
운동 기록, 칼로리 추적, 목표 달성에 대한 시각적 피드백을 제공하며, 프로필을 쉽게 관리할 수 있는 기능을 제공!

---

## 🔍 **주요 기능**:
1. **운동 기록 저장 & 관리 🏋️‍♀️**:
   - 사용자는 운동 종류, 소모 칼로리, 운동 시간을 기록
   - Firebase를 사용해 실시간으로 운동 데이터를 저장하고 로드
   - 운동 기록을 목록 형태로 확인하고, 삭제할 수 있는 기능 제공

2. **다이어트 목표 관리 🥗**:
   - 사용자는 설정한 칼로리 목표를 기준으로 소모한 칼로리와 목표를 비교하며 진행 상황을 추적
   - 목표 달성도를 시각적으로 표현한 그래프와 진행률을 보여줌

3. **회원가입 & 로그인 기능 🔑**:
   - Firebase 인증을 통해 사용자 계정을 만들고 로그인
   - 이메일과 비밀번호를 이용한 로그인/회원가입 기능 제공

4. **프로필 관리 👤**:
   - 사용자는 이름, 나이, 키 등의 개인 정보를 입력하고 수정
   - 수정된 프로필 정보를 실시간으로 저장하고 불러옴

5. **애니메이션 피드백 🎥**:
   - 운동 기록 및 목표 달성 상태를 애니메이션 그래프와 프로그래스 바로 시각적으로 표현하여 동기부여를 강화
   - 운동 기록에 대한 시각적 피드백을 제공하며, 다양한 애니메이션 효과로 앱 사용의 재미 추가

6. **어두운 모드 & 라이트 모드 🌙🌞**:
   - 다크 테마와 라이트 테마를 지원하여 사용자가 편안하게 사용
   - 안드로이드 12 이상에서 동적 색상 조정이 가능하여 시스템 테마에 맞춰 색상이 변경

---

## 🛠 **기술 스택**:

| Category           | Technologies                                          |
|--------------------|-------------------------------------------------------|
| **Architecture**    | MVVM, Clean Architecture                             |
| **DI**              | Dagger-Hilt                                          |
| **UI Framework**    | Jetpack Compose                                      |
| **State Management**| StateFlow, UiState                                   |
| **Database**        | Firebase Firestore                                   |
| **Authentication**  | Firebase Authentication                              |
| **Networking**      | Firebase (for storing user data)                     |
| **APIs**            | Firebase Auth, Firebase Firestore                    |
| **Design**          | Material3, Custom Animations                         |
| **Concurrency**     | Coroutines (suspend functions)                       |

---

## 🚀 **주요 기술 적용**:
### **1. Firebase**:
- **Firebase Authentication**을 사용하여 사용자의 로그인 및 회원가입을 처리
- **Firebase Firestore**를 활용하여 운동 기록 및 프로필 데이터를 저장하고 관리
  
### **2. Jetpack Compose**:
- **UI**는 모두 **Jetpack Compose**를 사용하여 선언적으로 작성되며, 동적이고 직관적인 UI를 제공

### **3. MVVM 아키텍처**:
- **MVVM (Model-View-ViewModel)** 패턴을 사용하여 앱의 비즈니스 로직과 UI를 명확히 분리
- **ViewModel**에서 데이터 상태를 관리하고, **UiState**를 사용해 UI 상태를 로딩, 성공, 실패 등으로 처리

### **4. 애니메이션 효과**:
- 운동 기록을 나타내는 **애니메이션 그래프**와 **프로그램 진척도를 보여주는 원형 프로그래스 바** 등을 사용하여 시각적 피드백을 강화

---

## 📱 **앱 화면 설명**:
### **1. 로그인 & 회원가입 화면**:
- 사용자는 이메일과 비밀번호로 로그인하거나 회원가입
- 로그인 성공 시, 메인 화면으로 이동

### **2. 운동 기록 화면**:
- **운동 추가 화면**: 사용자는 운동 종류, 칼로리, 시간을 입력하여 운동 기록을 추가
- **운동 목록 화면**: 기록된 운동을 확인하고, 삭제할 수 있는 기능을 제공

### **3. 목표 추적 화면**:
- 목표 달성도를 **애니메이션 그래프**로 시각적으로 표현하여 진행 상황을 직관적으로 확인

### **4. 프로필 화면**:
- 사용자는 자신의 프로필을 확인하고 수정. 수정된 정보는 실시간으로 Firebase에 저장

📌 **캡처 GIF**:  

| **시작화면** | **로그인 화면** |
|----------------------------------|----------------------------------|
| ![diety_splash](https://github.com/user-attachments/assets/e0963901-58ac-4cb4-93d0-46c1504e1843)|![diety_login](https://github.com/user-attachments/assets/887b17ea-db5c-4c30-80b6-913a45365b45)|

| **Diet 기록 남기는 화면(Diet Screen)** |
|----------------------------------|
| ![diety_record](https://github.com/user-attachments/assets/51f3caff-621c-486a-80cc-c93ea8c0daf6) |

| **Diet 기록에 따른 애니메이션 출력(Home Screen)** |
|----------------------------------|
| ![diety_home](https://github.com/user-attachments/assets/6f2968ec-57b5-4874-9824-7f78b6c4dc9a) |

| **프로필 수정(EditProfile Screen)** |
|----------------------------------|
 | ![diety_editprofile](https://github.com/user-attachments/assets/b6e53803-c8d8-4da5-a932-3d14316673c8)|
