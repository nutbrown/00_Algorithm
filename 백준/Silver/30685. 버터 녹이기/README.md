# [Silver II] 버터 녹이기 - 30685 

[문제 링크](https://www.acmicpc.net/problem/30685) 

### 성능 요약

메모리: 320460 KB, 시간: 2880 ms

### 분류

많은 조건 분기, 구현, 매개 변수 탐색, 시뮬레이션

### 제출 일자

2023년 12월 13일 15:39:20

### 문제 설명

<p>성현이는 바나나 푸딩을 만들기 위해 먼저 버터를 녹이려고 한다. 이를 위해 양쪽으로 무한히 뻗은 직선 모양의 프라이팬에 각각 홀수 높이의 버터 $N$개를 올려놓고, 열을 가해서 버터를 녹인다. 위치가 $x$인 곳에 올려진 버터는 처음에 구간 $[x,x]$를 차지하고, 열을 가하면 처음 버터가 올려진 부분의 높이가 1이 될 때까지 1초에 좌우로 1씩 퍼지게 된다. 즉, $[L,R]$이었던 버터는 1초 후 $[L-1,R+1]$를 차지하게 된다. 다음 예시는 위치 $x=5$, 높이 $h=7$인 버터가 녹는 과정이다.</p>

<table align="center" border="1" cellpadding="1" cellspacing="1" class="table table-bordered" style="width: 500px;">
	<tbody>
		<tr>
			<td style="text-align: center;"> </td>
			<td style="text-align: center;"><strong>0</strong></td>
			<td style="text-align: center;"><strong>1</strong></td>
			<td style="text-align: center;"><strong>2</strong></td>
			<td style="text-align: center;"><strong>3</strong></td>
			<td style="text-align: center;"><strong>4</strong></td>
			<td style="text-align: center;"><strong>5</strong></td>
			<td style="text-align: center;"><strong>6</strong></td>
			<td style="text-align: center;"><strong>7</strong></td>
			<td style="text-align: center;"><strong>8</strong></td>
			<td style="text-align: center;"><strong>9</strong></td>
		</tr>
		<tr>
			<td style="text-align: center;"><strong>0초</strong></td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">7</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
		</tr>
		<tr>
			<td style="text-align: center;"><strong>1초</strong></td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">5</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
		</tr>
		<tr>
			<td style="text-align: center;"><strong>2초</strong></td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">3</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
		</tr>
		<tr>
			<td style="text-align: center;"><strong>3초</strong></td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">0</td>
		</tr>
		<tr>
			<td style="text-align: center;"><strong>4초</strong></td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">0</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">1</td>
			<td style="text-align: center;">0</td>
		</tr>
	</tbody>
</table>

<p>이때 서로 다른 두 버터가 녹아서 섞이게 될 수 있다. 두 버터가 섞인다는 것은, 두 버터가 각각 $[l_1,r_1]$와 $[l_2,r_2]$에 놓였을 때 겹치는 구간이 생기는 것을 의미한다. 즉, $[l_1,r_1] \cap [l_2,r_2] \neq \emptyset$ 인 경우이다. 다음은 예제 1번의 상황을 그림으로 나타낸 것이다.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 375px; height: 168px;"></p>

<p style="text-align: center;">2초의 상황으로 두 버터가 섞이지 않는다.</p>

<p style="text-align: center;"><img alt="" src="" style="width: 376px; height: 93px;"></p>

<p style="text-align: center;">3초까지 가열하면 $x=2$에서 섞이게 된다.</p>

<p>성현이는 어떤 두 버터도 섞이지 않을 때까지만 버터를 녹이고 싶다. 그러나 성현이는 정수 시간만큼만 가열할 수 있다. 즉, 3초나 4초를 가열할 수는 있지만, 3.5초나 3.105초를 가열할 수는 없다. 직선 모양의 프라이팬에 처음 버터를 놓은 위치 $x$와 버터의 높이 $h$가 $N$개 주어졌을 때, 성현이가 얼마나 오랜 시간 버터를 가열할 수 있는지 알아보자.</p>

### 입력 

 <p>입력은 다음과 같이 주어진다.</p>

<div style="background:#eeeeee;border:1px solid #cccccc;padding:5px 10px;">
<p>$N$</p>

<p>$x_1$ $h_1$</p>

<p>$x_2$ $h_2$</p>

<p>$\cdots$</p>

<p>$x_{N-1}$ $h_{N-1}$</p>

<p>$x_N$ $h_N$</p>
</div>

<p>첫째 줄에 성현이가 올린 버터의 수 $N$이 주어진다.</p>

<p>이어 $N$개의 줄에 걸쳐 버터가 놓인 좌표 $x_i$와 버터의 높이 $h_i$가 공백으로 구분되어 주어진다.</p>

### 출력 

 <p>성현이가 얼마나 오랜 시간 버터를 가열할 수 있는지 출력한다. 만약 성현이가 $10^{100}$초 이상 가열할 수 있다면, <span style="color:#e74c3c;"><code>forever</code></span>를 출력한다.</p>

