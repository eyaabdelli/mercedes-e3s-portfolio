const API_BASE = "http://localhost:8080";
function credentials(){ return localStorage.getItem('e3sAuth') || ''; }
function authHeaders(json=false){ const h={}; if(json) h['Content-Type']='application/json'; const c=credentials(); if(c) h['Authorization']='Basic '+c; return h; }
function requireLogin(){ if(!credentials()) location.href='login.html'; }
async function api(url, options={}){ options.headers={...(options.headers||{}),...authHeaders(!!options.body)}; const r=await fetch(API_BASE+url,options); if(r.status===401||r.status===403){localStorage.removeItem('e3sAuth'); location.href='login.html'; throw new Error('Accès non autorisé');} return r; }
function logout(){localStorage.removeItem('e3sAuth');location.href='login.html';}
