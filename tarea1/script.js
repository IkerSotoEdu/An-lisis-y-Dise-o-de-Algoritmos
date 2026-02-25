const canvas = document.getElementById('game');
const ctx = canvas.getContext('2d');
const scoreEl = document.getElementById('score');
const restartBtn = document.getElementById('restart');

const W = canvas.width; const H = canvas.height;
const groundY = H - 30;

let running = true;
let score = 0;
let speed = 6;
let spawnTimer = 0;
let lastTime = 0;

const dino = {
  x: 50,
  w: 44,
  h: 44,
  y: groundY - 44,
  vy: 0,
  gravity: 0.9,
  jumpForce: -16,
  onGround: true
};

let obstacles = [];

function reset(){
  running = true;
  score = 0;
  speed = 6;
  spawnTimer = 0;
  dino.y = groundY - dino.h;
  dino.vy = 0;
  dino.onGround = true;
  obstacles = [];
  lastTime = performance.now();
  loop(lastTime);
}

function spawnObstacle(){
  const h = 20 + Math.random()*40;
  const w = 10 + Math.random()*30;
  obstacles.push({x: W + 10, y: groundY - h, w, h});
}

function rectsCollide(a,b){
  return a.x < b.x + b.w && a.x + a.w > b.x && a.y < b.y + b.h && a.y + a.h > b.y;
}

function update(delta){
  if(!running) return;
  dino.vy += dino.gravity * (delta/16);
  dino.y += dino.vy * (delta/16);
  if(dino.y > groundY - dino.h){ dino.y = groundY - dino.h; dino.vy = 0; dino.onGround = true; }

  spawnTimer -= delta;
  if(spawnTimer <= 0){
    spawnObstacle();
    spawnTimer = 800 + Math.random()*1200 - Math.min(score*2,600);
  }
  for(let i = obstacles.length-1;i>=0;i--){
    obstacles[i].x -= speed * (delta/16);
    if(obstacles[i].x + obstacles[i].w < 0) obstacles.splice(i,1);

    if(rectsCollide(dino,obstacles[i])){
      running = false;
      showGameOver();
    }
  }


  score += delta * 0.01;
  if(Math.floor(score) % 100 === 0) speed = 6 + Math.floor(score/100);
}

function draw(){

  ctx.clearRect(0,0,W,H);

  ctx.fillStyle = '#888';
  ctx.fillRect(0, groundY, W, 2);

  ctx.fillStyle = '#222';
  ctx.fillRect(dino.x, dino.y, dino.w, dino.h);
  
  ctx.fillStyle = '#333';
  obstacles.forEach(o => ctx.fillRect(o.x, o.y, o.w, o.h));
}

function loop(t){
  const delta = t - lastTime;
  lastTime = t;
  update(delta);
  draw();
  scoreEl.textContent = 'Puntuación: ' + Math.floor(score);
  if(running) requestAnimationFrame(loop);
}

function jump(){
  if(!running) return;
  if(dino.onGround){ dino.vy = dino.jumpForce; dino.onGround = false; }
}

function showGameOver(){
  scoreEl.innerHTML = 'Puntuación: ' + Math.floor(score) + ' <span class="game-over">- GAME OVER</span>';
}


window.addEventListener('keydown', e => {
  if(e.code === 'Space' || e.code === 'ArrowUp'){
    e.preventDefault();
    if(!running) reset(); else jump();
  }
});

canvas.addEventListener('mousedown', e => { if(!running) reset(); else jump(); });
canvas.addEventListener('touchstart', e => { e.preventDefault(); if(!running) reset(); else jump(); }, {passive:false});
restartBtn.addEventListener('click', reset);

lastTime = performance.now();
loop(lastTime);