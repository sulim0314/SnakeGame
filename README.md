----
# 스네이크 게임 만들기
---



## 게임 설명  


1. **전체 맵 그리기**  
    * 가로 400픽셀 x 세로 400픽셀의 스크린 창을 생성.  
    * 스네이크가 이동 가능한 칸의 크기는 20 x 20 픽셀로 고정.
    * GUI 도구를 이용하여 검은색 선을 그어 전체 맵을 표현.
  
2. **먹이 생성**  
    * 먹이는 맵 20 x 20 칸에 랜덤하게 생성되며, 20 x 20픽셀의 붉은색 box로 표현.  
    * 뱀이 먹이를 먹을 경우, 먹이의 위치는 다시 랜덤하게 설정.
  
3. **뱀의 이동**
    * 뱀의 머리는 맵 20 x 20 칸에 랜덤하게 생성되며, 20 x 20픽셀의 초록색 box로 표현.  
    * 뱀의 머리는 현재 정해진 방향으로 자동으로 이동.  
    * 방향키 상,하,좌,우의 입력이 있을 때마다 뱀의 머리가 이동하는 방향이 해당 화살표 방향으로 변경.  
  
4. **뱀의 성장**
    * 뱀이 먹이를 먹을 때마다 뱀의 크기는 1칸씩 늘어남.  
    * 뱀이 먹이를 먹을 때마다 먹이의 위치는 다시 설정되며, 먹이의 위치는 반드시 현재 뱀의 위치와 중복되지 않게 생성.   
  
5. **게임 오버 조건**
    * 뱀의 머리가 자신의 몸에 닿거나, 뱀의 머리가 화면 밖으로 나갈 경우 게임이 종료.
    * 게임 종료 시 "게임 오버입니다."라는 메시지 알림.  



## 목표
* 선 그리기, 도형 채우기 등 기본적인 GUI 사용법 익히기
* 게임 제작에 대한 기본적인 감 익히기
* 물체의 충돌 체크 개념 익히기
* 뱀의 이동 알고리즘 익히기

----