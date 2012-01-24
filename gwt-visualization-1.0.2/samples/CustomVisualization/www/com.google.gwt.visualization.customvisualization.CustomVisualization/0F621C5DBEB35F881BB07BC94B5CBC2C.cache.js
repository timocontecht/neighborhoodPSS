(function(){var $gwt_version = "1.5.3";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'com.google.gwt.visualization.customvisualization.CustomVisualization',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var gb='',kb=', Row size: ',xb=', Size: ',Fb=':',Db='<\/b>',Cb='<b>',rb='Cannot create a column with a negative index: ',sb='Cannot create a row with a negative index: ',fc='CustomVisualization',v='DOMMouseScroll',wb='Index: ',jc='Integer;',mc='JavaScriptObject$;',kc='Object;',jb='Row index: ',hc='Widget;',lc='[Lcom.google.gwt.core.client.',gc='[Lcom.google.gwt.user.client.ui.',ic='[Ljava.lang.',vb='__widgetID',Ab='backgroundColor',l='blur',m='change',x='click',ub='col',tb='colgroup',ec='com.google.gwt.visualization.customvisualization.client.CustomVisualizationEntryPoint',w='contextmenu',cb='dblclick',t='error',nb='focus',yb='keydown',dc='keypress',nc='keyup',fb='left',oc='load',pc='losecapture',bc='moduleStartup',n='mousedown',o='mousemove',p='mouseout',q='mouseover',r='mouseup',u='mousewheel',cc='onModuleLoadStart',bb='onblur',y='onclick',eb='oncontextmenu',db='ondblclick',ab='onfocus',D='onkeydown',E='onkeypress',F='onkeyup',z='onmousedown',B='onmousemove',A='onmouseup',C='onmousewheel',ib='position',s='scroll',zb='select',Eb='selection changed',ac='startup',pb='table',lb='tagName',qb='tbody',mb='td',hb='top',ob='tr',Bb='white';var _;function hn(a){return (this==null?null:this)===(a==null?null:a)}
function jn(){return this.$H||(this.$H=++gd)}
function fn(){}
_=fn.prototype={};_.eQ=hn;_.hC=jn;_.tM=hs;_.tI=1;function tc(b){var a,c;c=[];for(a=0;a<b.length;++a){c[a]=b[a]}c.constructor=$wnd.Array;return c}
function Ac(b,a){return b.tM==hs||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function Cc(a){return a.tM==hs||a.tI==2?a.hC():a.$H||(a.$H=++gd)}
var gd=0;function od(b){var a=b.firstChild;while(a&&a.nodeType!=1)a=a.nextSibling;return a}
function ee(e,c){var d=[null,0,false,[0,0]];var f=d[e];var a=new Array(c);for(var b=0;b<c;++b){a[b]=f}return a}
function fe(a,f,c,b,e){var d;d=ee(e,b);ge(a,f,c,d);return d}
function ge(b,d,c,a){if(!he){he=new ae()}ke(a,he);a.tI=d;a.qI=c;return a}
function ie(a,b,c){if(c!=null){if(a.qI>0&&!ne(c.tI,a.qI)){throw new em()}if(a.qI<0&&(c.tM==hs||c.tI==2)){throw new em()}}return a[b]=c}
function ke(a,c){for(var b in c){var d=c[b];if(d){a[b]=d}}return a}
function ae(){}
_=ae.prototype=new fn();_.tI=0;_.length=0;_.qI=0;var he=null;function oe(b,a){return b&&!!Ae[b][a]}
function ne(b,a){return b&&Ae[b][a]}
function pe(b,a){if(b!=null&&!ne(b.tI,a)){throw new im()}return b}
function se(b,a){return b!=null&&oe(b.tI,a)}
var Ae=[{},{},{1:1,17:1,18:1,19:1},{2:1,17:1},{2:1,17:1},{2:1,17:1},{2:1,17:1},{9:1},{4:1,5:1,9:1},{4:1,5:1,8:1,9:1},{4:1,5:1,8:1,9:1},{4:1,5:1,8:1,9:1},{4:1,5:1,9:1},{4:1,5:1,8:1,9:1},{4:1,5:1,8:1,9:1},{4:1,5:1,6:1,8:1,9:1},{3:1},{4:1,5:1,6:1,8:1,9:1},{13:1},{13:1,17:1},{13:1,17:1},{4:1,5:1,9:1},{4:1,5:1,9:1},{7:1},{2:1,17:1},{2:1,17:1},{2:1,17:1},{2:1,17:1},{2:1,17:1},{17:1,20:1},{2:1,17:1},{2:1,17:1},{11:1,17:1,19:1,20:1},{18:1},{18:1},{2:1,17:1},{15:1},{15:1},{12:1},{12:1},{12:1},{15:1},{14:1,17:1},{15:1,17:1},{12:1},{2:1,17:1},{10:1}];function sf(b,a,c){var d;if(a==vf){if(sg(b)==8192){vf=null}}d=rf;rf=b;try{c.z(b)}finally{rf=d}}
function uf(a){return true}
function wf(a,b){ug();ng(a,b)}
var rf=null,vf=null;function Bf(a){cg();if(!Df){Df=Bq(new Aq())}Cq(Df,a)}
function Ef(){var a;if(Df){for(a=sp(new qp(),Df);a.a<a.b.ab();){pe(vp(a),3);sj()}}}
function Ff(){var a,b;b=null;if(Df){for(a=sp(new qp(),Df);a.a<a.b.ab();){pe(vp(a),3);b=null}}return b}
function bg(){__gwt_initHandlers(function(){},function(){return Ff()},function(){Ef()})}
function cg(){if(!ag){bg();ag=true}}
var Df=null,ag=false;function sg(a){switch(a.type){case l:return 4096;case m:return 1024;case x:return 1;case cb:return 2;case nb:return 2048;case yb:return 128;case dc:return 256;case nc:return 512;case oc:return 32768;case pc:return 8192;case n:return 4;case o:return 64;case p:return 32;case q:return 16;case r:return 8;case s:return 16384;case t:return 65536;case u:return 131072;case v:return 131072;case w:return 262144;}}
function ug(){if(!wg){lg();wg=true}}
var wg=false;function kg(d,a){var b=d.children.length;for(var c=0;c<b;++c){if(a===d.children[c]){return c}}return -1}
function lg(){qg=function(){var c=og;og=this;if($wnd.event.returnValue==null){$wnd.event.returnValue=true;if(!uf($wnd.event)){og=c;return}}var b,a=this;while(a&&!(b=a.__listener)){a=a.parentElement}if(b){if(b!=null&&oe(b.tI,4)&&!(b!=null&&(b.tM!=hs&&b.tI!=2))){sf($wnd.event,a,b)}}og=c};pg=function(){var a=$doc.createEventObject();this.fireEvent(y,a);if(this.__eventBits&2){qg.call(this)}};var e=function(){qg.call($doc.body)};var d=function(){pg.call($doc.body)};$doc.body.attachEvent(y,e);$doc.body.attachEvent(z,e);$doc.body.attachEvent(A,e);$doc.body.attachEvent(B,e);$doc.body.attachEvent(C,e);$doc.body.attachEvent(D,e);$doc.body.attachEvent(E,e);$doc.body.attachEvent(F,e);$doc.body.attachEvent(ab,e);$doc.body.attachEvent(bb,e);$doc.body.attachEvent(db,d);$doc.body.attachEvent(eb,e)}
function mg(c,a,b){if(b>=c.children.length)c.appendChild(a);else c.insertBefore(a,c.children[b])}
function ng(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?qg:null;if(b&3)c.ondblclick=a&3?pg:null;if(b&4)c.onmousedown=a&4?qg:null;if(b&8)c.onmouseup=a&8?qg:null;if(b&16)c.onmouseover=a&16?qg:null;if(b&32)c.onmouseout=a&32?qg:null;if(b&64)c.onmousemove=a&64?qg:null;if(b&128)c.onkeydown=a&128?qg:null;if(b&256)c.onkeypress=a&256?qg:null;if(b&512)c.onkeyup=a&512?qg:null;if(b&1024)c.onchange=a&1024?qg:null;if(b&2048)c.onfocus=a&2048?qg:null;if(b&4096)c.onblur=a&4096?qg:null;if(b&8192)c.onlosecapture=a&8192?qg:null;if(b&16384)c.onscroll=a&16384?qg:null;if(b&32768)c.onload=a&32768?qg:null;if(b&65536)c.onerror=a&65536?qg:null;if(b&131072)c.onmousewheel=a&131072?qg:null;if(b&262144)c.oncontextmenu=a&262144?qg:null}
var og=null,pg=null,qg=null;function Fj(b,a){b.i=a}
function Dj(){}
_=Dj.prototype=new fn();_.tI=7;_.i=null;function qk(a){if(a.v()){throw new tm()}a.g=true;a.i.__listener=a;a.m();a.B()}
function rk(a){if(!a.v()){throw new tm()}try{a.C()}finally{a.n();a.i.__listener=null;a.g=false}}
function sk(a){if(se(a.h,8)){pe(a.h,8).D(a)}else if(a.h){throw new tm()}}
function tk(c,b){var a;a=c.h;if(!b){if(!!a&&a.v()){c.A()}c.h=null}else{if(a){throw new tm()}c.h=b;if(b.v()){c.y()}}}
function uk(){}
function vk(){}
function wk(){return this.g}
function xk(){qk(this)}
function yk(a){}
function zk(){rk(this)}
function Ak(){}
function Bk(){}
function ak(){}
_=ak.prototype=new Dj();_.m=uk;_.n=vk;_.v=wk;_.y=xk;_.z=yk;_.A=zk;_.B=Ak;_.C=Bk;_.tI=8;_.g=false;_.h=null;function gj(){var a,b;for(b=this.w();b.u();){a=pe(b.x(),5);a.y()}}
function hj(){var a,b;for(b=this.w();b.u();){a=pe(b.x(),5);a.A()}}
function ij(){}
function jj(){}
function ej(){}
_=ej.prototype=new ak();_.m=gj;_.n=hj;_.B=ij;_.C=jj;_.tI=9;function Fg(c,a,b){sk(a);jk(c.a,a);b.appendChild(a.i);tk(a,c)}
function bh(b,c){var a;if(c.h!=b){return false}tk(c,null);a=c.i;a.parentElement.removeChild(a);ok(b.a,c);return true}
function ch(){return ek(new ck(),this.a)}
function dh(a){return bh(this,a)}
function Dg(){}
_=Dg.prototype=new ej();_.w=ch;_.D=dh;_.tI=10;function zg(a,b){Fg(a,b,a.i)}
function Bg(a){a.style[fb]=gb;a.style[hb]=gb;a.style[ib]=gb}
function Cg(b){var a;a=bh(this,b);if(a){Bg(b.i)}return a}
function yg(){}
_=yg.prototype=new Dg();_.D=Cg;_.tI=11;function gh(a,b){if(a.e){throw new tm()}sk(b);Fj(a,b.i);a.e=b;tk(b,a)}
function hh(){if(this.e){return this.e.g}return false}
function ih(){qk(this.e);this.i.__listener=this}
function jh(a){zi(this.e,a)}
function kh(){rk(this.e)}
function eh(){}
_=eh.prototype=new ak();_.v=hh;_.y=ih;_.z=jh;_.A=kh;_.tI=12;_.e=null;function oi(b,a){if(!b.e){b.e=zj(new yj());wf(b.i,1|(b.i.__eventBits||0))}Cq(b.e,a)}
function pi(c,a){var b;b=c.a.rows.length;if(a>=b||a<0){throw xm(new wm(),jb+a+kb+b)}}
function ri(d){var a,b,c;for(c=0;c<d.a.rows.length;++c){for(b=0;b<(pi(d,c),d.a.rows[c].cells.length);++b){a=wi(d,c,b);if(a){Ai(d,a)}}}}
function vi(d,b){var a,c,e;c=b.srcElement;for(;c;c=c.parentElement){if(xn(c[lb]==null?null:String(c[lb]),mb)){e=c.parentElement;a=e.parentElement;if(a==d.a){return c}}if(c==d.a){return null}}return null}
function wi(e,d,b){var a,c;c=e.b.a.a.rows[d].cells[b];a=od(c);if(!a){return null}else{return ii(e.f,a)}}
function xi(b,a){var c;if(a!=b.a.rows.length){pi(b,a)}c=$doc.createElement(ob);mg(b.a,c,a);return a}
function yi(d,c,a){var b,e;b=od(c);e=null;if(b){e=ii(d.f,b)}if(e){Ai(d,e);return true}else{if(a){c.innerHTML=gb}return false}}
function zi(f,c){var a,b,d,e,g;switch(sg(c)){case 1:{if(f.e){e=vi(f,c);if(!e){return}g=e.parentElement;a=g.parentElement;d=kg(a,g);b=kg(g,e);Bj(f.e,d,b)}break}}}
function Ai(b,c){var a;if(c.h!=b){return false}tk(c,null);a=c.i;a.parentElement.removeChild(a);ji(b.f,a);return true}
function Ci(b,a){b.c=a;Ch(b.c)}
function Di(f,d,a,c){var e,b;rh(f,d,a);e=(b=f.b.a.a.rows[d].cells[a],yi(f,b,c==null),b);if(c!=null){e.innerHTML=c||gb}}
function Ei(f,c,a,e){var d,b;rh(f,c,a);d=(b=f.b.a.a.rows[c].cells[a],yi(f,b,e==null),b);if(e!=null){d.innerText=e||gb}}
function Fi(){return ai(new Eh(),this.f)}
function aj(a){zi(this,a)}
function bj(a){return Ai(this,a)}
function uh(){}
_=uh.prototype=new ej();_.w=Fi;_.z=aj;_.D=bj;_.tI=13;_.a=null;_.b=null;_.c=null;_.d=null;_.e=null;function ph(a){a.f=gi(new Dh());a.d=$doc.createElement(pb);a.a=$doc.createElement(qb);a.d.appendChild(a.a);a.i=a.d;a.b=nh(new mh(),a);Ci(a,Ah(new zh(),a));return a}
function rh(e,d,b){var a,c;sh(e,d);if(b<0){throw xm(new wm(),rb+b)}a=(pi(e,d),e.a.rows[d].cells.length);c=b+1-a;if(c>0){th(e.a,d,c)}}
function sh(d,b){var a,c;if(b<0){throw xm(new wm(),sb+b)}c=d.a.rows.length;for(a=c;a<=b;++a){xi(d,a)}}
function th(f,d,c){var e=f.rows[d];for(var b=0;b<c;b++){var a=$doc.createElement(mb);e.appendChild(a)}}
function lh(){}
_=lh.prototype=new uh();_.tI=14;function vh(){}
_=vh.prototype=new fn();_.tI=0;_.a=null;function nh(b,a){b.a=a;return b}
function mh(){}
_=mh.prototype=new vh();_.tI=0;function Ah(b,a){b.b=a;return b}
function Ch(a){if(!a.a){a.a=$doc.createElement(tb);mg(a.b.d,a.a,0);a.a.appendChild($doc.createElement(ub))}}
function zh(){}
_=zh.prototype=new fn();_.tI=0;_.a=null;_.b=null;function gi(a){a.a=Bq(new Aq());return a}
function ii(d,b){var c,a;c=(a=b[vb],a==null?-1:a);if(c<0){return null}return pe(Eq(d.a,c),5)}
function ji(d,b){var c,a;c=(a=b[vb],a==null?-1:a);b[vb]=null;ar(d.a,c,null)}
function Dh(){}
_=Dh.prototype=new fn();_.tI=0;function ai(b,a){b.b=a;ci(b);return b}
function ci(a){while(++a.a<a.b.a.b){if(Eq(a.b.a,a.a)!=null){return}}}
function di(){return this.a<this.b.a.b}
function ei(){var a;if(this.a>=this.b.a.b){throw new as()}a=pe(Eq(this.b.a,this.a),5);ci(this);return a}
function Eh(){}
_=Eh.prototype=new fn();_.u=di;_.x=ei;_.tI=0;_.a=-1;_.b=null;function rj(){rj=hs;vj=kr(new jr());wj=or(new nr())}
function qj(b,a){rj();b.a=ik(new bk());b.i=a;qk(b);return b}
function sj(){var b,a;rj();var c,d;for(d=(b=ko(new jo(),tq(wj.a).b.a),dq(new cq(),b));d.a.u();){c=pe((a=d.a.x(),a.q()),5);if(c.v()){c.A()}}}
function uj(b){rj();var a,c;c=pe(hp(vj,b),6);if(c){return c}a=null;if(b!=null){if(!(a=$doc.getElementById(b))){return null}}if(vj.d==0){Bf(new lj())}if(!a){c=oj(new nj())}else{c=qj(new kj(),a)}np(vj,b,c);pr(wj,c);return c}
function kj(){}
_=kj.prototype=new yg();_.tI=15;var vj,wj;function lj(){}
_=lj.prototype=new fn();_.tI=16;function pj(){pj=hs;rj()}
function oj(a){pj();qj(a,$doc.body);return a}
function nj(){}
_=nj.prototype=new kj();_.tI=17;function co(a,b){var c;while(a.u()){c=a.x();if(b==null?c==null:Ac(b,c)){return a}}return null}
function fo(a){throw new En()}
function go(b){var a;a=co(this.w(),b);return !!a}
function bo(){}
_=bo.prototype=new fn();_.k=fo;_.l=go;_.tI=0;function Ap(a){this.j(this.ab(),a);return true}
function zp(b,a){throw new En()}
function Bp(a,b){if(a<0||a>=b){Ep(a,b)}}
function Cp(e){var a,b,c,d,f;if((e==null?null:e)===(this==null?null:this)){return true}if(!(e!=null&&oe(e.tI,13))){return false}f=pe(e,13);if(this.ab()!=f.ab()){return false}c=sp(new qp(),this);d=f.w();while(c.a<c.b.ab()){a=vp(c);b=vp(d);if(!(a==null?b==null:Ac(a,b))){return false}}return true}
function Dp(){var a,b,c;b=1;a=sp(new qp(),this);while(a.a<a.b.ab()){c=vp(a);b=31*b+(c==null?0:Cc(c));b=~~b}return b}
function Ep(a,b){throw xm(new wm(),wb+a+xb+b)}
function Fp(){return sp(new qp(),this)}
function pp(){}
_=pp.prototype=new bo();_.k=Ap;_.j=zp;_.eQ=Cp;_.hC=Dp;_.w=Fp;_.tI=18;function Bq(a){a.a=fe(af,0,0,0,0);a.b=0;return a}
function Cq(b,a){ie(b.a,b.b++,a);return true}
function Eq(b,a){Bp(a,b.b);return b.a[a]}
function Fq(c,b,a){for(;a<c.b;++a){if(gs(b,c.a[a])){return a}}return -1}
function ar(d,a,b){var c;c=(Bp(a,d.b),d.a[a]);ie(d.a,a,b);return c}
function cr(a){return ie(this.a,this.b++,a),true}
function br(a,b){if(a<0||a>this.b){Ep(a,this.b)}this.a.splice(a,0,b);++this.b}
function dr(a){return Fq(this,a,0)!=-1}
function er(a){return Bp(a,this.b),this.a[a]}
function fr(){return this.b}
function Aq(){}
_=Aq.prototype=new pp();_.k=cr;_.j=br;_.l=dr;_.t=er;_.ab=fr;_.tI=19;_.a=null;_.b=0;function zj(a){a.a=fe(af,0,0,0,0);a.b=0;return a}
function Bj(e,d,a){var b,c;for(c=sp(new qp(),e);c.a<c.b.ab();){b=pe(vp(c),7);b.a.b=bn(a);b.a.c=bn(d-1);$wnd.google.visualization.events.trigger(b.a.d,zb,null)}}
function yj(){}
_=yj.prototype=new Aq();_.tI=20;function ik(a){a.a=fe(Ee,0,5,4,0);return a}
function jk(a,b){mk(a,b,a.b)}
function lk(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function mk(d,e,a){var b,c;if(a<0||a>d.b){throw new wm()}if(d.b==d.a.length){c=fe(Ee,0,5,d.a.length*2,0);for(b=0;b<d.a.length;++b){ie(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){ie(d.a,b,d.a[b-1])}ie(d.a,a,e)}
function nk(c,b){var a;if(b<0||b>=c.b){throw new wm()}--c.b;for(a=b;a<c.b;++a){ie(c.a,a,c.a[a+1])}ie(c.a,c.b,null)}
function ok(b,c){var a;a=lk(b,c);if(a==-1){throw new as()}nk(b,a)}
function bk(){}
_=bk.prototype=new fn();_.tI=0;_.a=null;_.b=0;function ek(b,a){b.b=a;return b}
function gk(){return this.a<this.b.b-1}
function hk(){if(this.a>=this.b.b){throw new as()}return this.b.a[++this.a]}
function ck(){}
_=ck.prototype=new fn();_.u=gk;_.x=hk;_.tI=0;_.a=-1;_.b=null;function hl(b,c,a){var d;d=Dl(new pl());d.d=c;zg(uj(a.id),d);if(d){jl(c)}return d}
function jl(b){b.getSelection=function(){return this.gwt_vis.r()};b.setSelection=function(a){this.gwt_vis.E(a)}}
function kl(d,c){$wnd[d]=function(a){this.gwt_vis=hl(c,this,a)};$wnd[d].prototype.draw=function(a,b){this.gwt_vis.o(a,b)}}
function el(){}
_=el.prototype=new eh();_.tI=21;_.d=null;function Dl(a){a.a=ph(new lh());gh(a,a.a);return a}
function Fl(b,c){var a,d;ri(this.a);if(c){this.a.i.style[Ab]=c.backgroundColor||Bb}for(a=0;a<b.getNumberOfColumns();++a){Di(this.a,0,a,Cb+b.getColumnLabel(a)+Db)}for(d=0;d<b.getNumberOfRows();++d){for(a=0;a<b.getNumberOfColumns();++a){Ei(this.a,d+1,a,b.getFormattedValue(d,a))}}oi(this.a,rl(new ql(),this))}
function am(){return tc(ge(De,0,-1,[{row:this.c.a,column:this.b.a}]))}
function bm(a){$wnd.alert(Eb)}
function pl(){}
_=pl.prototype=new el();_.o=Fl;_.r=am;_.E=bm;_.tI=22;_.b=null;_.c=null;function rl(b,a){b.a=a;return b}
function ql(){}
_=ql.prototype=new fn();_.tI=23;_.a=null;function Bl(a){if($wnd.onLoadCallback!=undefined){$wnd.onLoadCallback(a)}}
function yl(){}
_=yl.prototype=new fn();_.tI=0;function Cn(){}
_=Cn.prototype=new fn();_.tI=3;function rm(){}
_=rm.prototype=new Cn();_.tI=4;function kn(){}
_=kn.prototype=new rm();_.tI=5;function em(){}
_=em.prototype=new kn();_.tI=25;function lm(c,a){var b;b=new hm();return b}
function hm(){}
_=hm.prototype=new fn();_.tI=0;function im(){}
_=im.prototype=new kn();_.tI=28;function tm(){}
_=tm.prototype=new kn();_.tI=30;function xm(b,a){return b}
function wm(){}
_=wm.prototype=new kn();_.tI=31;function dn(){}
_=dn.prototype=new fn();_.tI=29;function Dm(a,b){a.a=b;return a}
function Fm(a){return a!=null&&oe(a.tI,11)&&pe(a,11).a==this.a}
function an(){return this.a}
function bn(a){var b,c;if(a>-129&&a<128){b=a+128;c=(Bm(),Cm)[b];if(!c){c=Cm[b]=Dm(new zm(),a)}return c}return Dm(new zm(),a)}
function zm(){}
_=zm.prototype=new dn();_.eQ=Fm;_.hC=an;_.tI=32;_.a=0;function Bm(){Bm=hs;Cm=fe(Fe,0,11,256,0)}
var Cm;function xn(b,a){if(a==null)return false;return b==a||b.toLowerCase()==a.toLowerCase()}
function An(a){if(!(a!=null&&oe(a.tI,1))){return false}return String(this)==a}
function Bn(){return tn(this)}
_=String.prototype;_.eQ=An;_.hC=Bn;_.tI=2;function on(){on=hs;pn={};sn={}}
function qn(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function tn(c){on();var a=Fb+c;var b=sn[a];if(b!=null){return b}b=pn[a];if(b==null){b=qn(c)}un();return sn[a]=b}
function un(){if(rn==256){pn=sn;sn={};rn=0}++rn}
var pn,rn=0,sn;function En(){}
_=En.prototype=new kn();_.tI=35;function tq(b){var a;a=oo(new io(),b);return iq(new bq(),b,a)}
function uq(c){var a,b,d,e,f;if((c==null?null:c)===(this==null?null:this)){return true}if(!(c!=null&&oe(c.tI,14))){return false}e=pe(c,14);if(pe(this,14).d!=e.d){return false}for(b=ko(new jo(),oo(new io(),e).a);up(b.a);){a=pe(vp(b.a),12);d=a.q();f=a.s();if(!(d==null?pe(this,14).c:d!=null&&oe(d.tI,1)?jp(pe(this,14),pe(d,1)):ip(pe(this,14),d,~~Cc(d)))){return false}if(!gs(f,d==null?pe(this,14).b:d!=null&&oe(d.tI,1)?pe(this,14).e[Fb+pe(d,1)]:fp(pe(this,14),d,~~Cc(d)))){return false}}return true}
function vq(){var a,b,c;c=0;for(b=ko(new jo(),oo(new io(),pe(this,14)).a);up(b.a);){a=pe(vp(b.a),12);c+=a.hC();c=~~c}return c}
function aq(){}
_=aq.prototype=new fn();_.eQ=uq;_.hC=vq;_.tI=0;function ap(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.k(a[f])}}}}
function bp(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=Eo(e,c.substring(1));a.k(b)}}}
function ep(b,a){return a==null?b.c:a!=null&&oe(a.tI,1)?jp(b,pe(a,1)):ip(b,a,~~Cc(a))}
function hp(b,a){return a==null?b.b:a!=null&&oe(a.tI,1)?b.e[Fb+pe(a,1)]:fp(b,a,~~Cc(a))}
function fp(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.q();if(h.p(g,d)){return c.s()}}}return null}
function ip(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.q();if(h.p(g,d)){return true}}}return false}
function jp(b,a){return Fb+a in b.e}
function np(b,a,c){return a==null?lp(b,c):a!=null&&oe(a.tI,1)?mp(b,pe(a,1),c):kp(b,a,c,~~Cc(a))}
function kp(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.q();if(i.p(g,d)){var h=c.s();c.F(j);return h}}}else{a=i.a[e]=[]}var c=zr(new yr(),g,j);a.push(c);++i.d;return null}
function lp(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function mp(d,a,e){var b,c=d.e;a=Fb+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function op(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Ac(a,b)}
function ho(){}
_=ho.prototype=new aq();_.p=op;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function yq(b){var a,c,d;if((b==null?null:b)===(this==null?null:this)){return true}if(!(b!=null&&oe(b.tI,15))){return false}c=pe(b,15);if(c.ab()!=this.ab()){return false}for(a=c.w();a.u();){d=a.x();if(!this.l(d)){return false}}return true}
function zq(){var a,b,c;a=0;for(b=this.w();b.u();){c=b.x();if(c!=null){a+=Cc(c);a=~~a}}return a}
function wq(){}
_=wq.prototype=new bo();_.eQ=yq;_.hC=zq;_.tI=36;function oo(b,a){b.a=a;return b}
function qo(c){var a,b,d;if(c!=null&&oe(c.tI,12)){a=pe(c,12);b=a.q();if(ep(this.a,b)){d=hp(this.a,b);return mr(a.s(),d)}}return false}
function ro(){return ko(new jo(),this.a)}
function so(){return this.a.d}
function io(){}
_=io.prototype=new wq();_.l=qo;_.w=ro;_.ab=so;_.tI=37;_.a=null;function ko(c,b){var a;c.b=b;a=Bq(new Aq());if(c.b.c){Cq(a,uo(new to(),c.b))}bp(c.b,a);ap(c.b,a);c.a=sp(new qp(),a);return c}
function mo(){return up(this.a)}
function no(){return pe(vp(this.a),12)}
function jo(){}
_=jo.prototype=new fn();_.u=mo;_.x=no;_.tI=0;_.a=null;_.b=null;function qq(b){var a;if(b!=null&&oe(b.tI,12)){a=pe(b,12);if(gs(this.q(),a.q())&&gs(this.s(),a.s())){return true}}return false}
function rq(){var a,b;a=0;b=0;if(this.q()!=null){a=Cc(this.q())}if(this.s()!=null){b=Cc(this.s())}return a^b}
function oq(){}
_=oq.prototype=new fn();_.eQ=qq;_.hC=rq;_.tI=38;function uo(b,a){b.a=a;return b}
function wo(){return null}
function xo(){return this.a.b}
function yo(a){return lp(this.a,a)}
function to(){}
_=to.prototype=new oq();_.q=wo;_.s=xo;_.F=yo;_.tI=39;_.a=null;function Ao(c,a,b){c.b=b;c.a=a;return c}
function Co(){return this.a}
function Do(){return this.b.e[Fb+this.a]}
function Eo(b,a){return Ao(new zo(),a,b)}
function Fo(a){return mp(this.b,this.a,a)}
function zo(){}
_=zo.prototype=new oq();_.q=Co;_.s=Do;_.F=Fo;_.tI=40;_.a=null;_.b=null;function sp(b,a){b.b=a;return b}
function up(a){return a.a<a.b.ab()}
function vp(a){if(a.a>=a.b.ab()){throw new as()}return a.b.t(a.a++)}
function wp(){return this.a<this.b.ab()}
function xp(){return vp(this)}
function qp(){}
_=qp.prototype=new fn();_.u=wp;_.x=xp;_.tI=0;_.a=0;_.b=null;function iq(b,a,c){b.a=a;b.b=c;return b}
function lq(a){return ep(this.a,a)}
function mq(){var a;return a=ko(new jo(),this.b.a),dq(new cq(),a)}
function nq(){return this.b.a.d}
function bq(){}
_=bq.prototype=new wq();_.l=lq;_.w=mq;_.ab=nq;_.tI=41;_.a=null;_.b=null;function dq(a,b){a.a=b;return a}
function gq(){return this.a.u()}
function hq(){var a;return a=this.a.x(),a.q()}
function cq(){}
_=cq.prototype=new fn();_.u=gq;_.x=hq;_.tI=0;_.a=null;function kr(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0;return a}
function mr(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Ac(a,b)}
function jr(){}
_=jr.prototype=new ho();_.tI=42;function or(a){a.a=kr(new jr());return a}
function pr(c,a){var b;b=np(c.a,a,c);return b==null}
function rr(b){var a;return a=np(this.a,b,this),a==null}
function sr(a){return ep(this.a,a)}
function tr(){var a;return a=ko(new jo(),tq(this.a).b.a),dq(new cq(),a)}
function ur(){return this.a.d}
function nr(){}
_=nr.prototype=new wq();_.k=rr;_.l=sr;_.w=tr;_.ab=ur;_.tI=43;_.a=null;function zr(b,a,c){b.a=a;b.b=c;return b}
function Br(){return this.a}
function Cr(){return this.b}
function Er(b){var a;a=this.b;this.b=b;return a}
function yr(){}
_=yr.prototype=new oq();_.q=Br;_.s=Cr;_.F=Er;_.tI=44;_.a=null;_.b=null;function as(){}
_=as.prototype=new kn();_.tI=45;function gs(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&Ac(a,b)}
function cm(){!!$stats&&$stats({moduleName:$moduleName,subSystem:ac,evtGroup:bc,millis:(new Date()).getTime(),type:cc,className:ec});kl(fc,new yl());Bl($moduleName)}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{cm()}catch(a){b(d)}else{cm()}}
function hs(){}
var Ee=lm(gc,hc),Fe=lm(ic,jc),af=lm(ic,kc),De=lm(lc,mc);$stats && $stats({moduleName:'com.google.gwt.visualization.customvisualization.CustomVisualization',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (com_google_gwt_visualization_customvisualization_CustomVisualization) {  var __gwt_initHandlers = com_google_gwt_visualization_customvisualization_CustomVisualization.__gwt_initHandlers;  com_google_gwt_visualization_customvisualization_CustomVisualization.onScriptLoad(gwtOnLoad);}})();