function [t,y] = rkf(f,a,b,y0,h,hmin,hmax,tol)
t(1) = a;
y = y0;
h = min(h,b-a);
A = [        0,          0,          0,           0,      0,    0;
1/4,          0,          0,           0,      0,    0;
3/32,       9/32,          0,           0,      0,    0;
1932/2197, -7200/2197,  7296/2197,           0,      0,    0;
439/216,         -8,   3680/513,   -845/4104,      0,    0;
-  8/27,          2, -3544/2565,   1859/4104, -11/40,    0 ];
bb = [  25/216,          0,  1408/2565,   2197/4104,   -1/5,    0 ]';
b1 = [  16/135,          0, 6656/12825, 28561/56430,  -9/50, 2/55 ]';
er = bb - b1;
s = length(b1);
d = length(y0);
kk = zeros(d,s);
nmax=10000;
for n=1:nmax
% compute stage derivatives
for i=1:s
z = y0;
for j=1:i-1
z = z + h*A(i,j)*kk(:,j);
end
kk(:,i) = f(z); % this line becomes more complicated in sdirk.m
end
% estimate the truncation error
R = norm(kk*er);  % book divides by h since they include h with kk
if (R<tol)   % accept step
t(end+1) = t(end) + h;
y(:,end+1) = y0 + h*kk*bb;
y0 = y(:,end);   % end refers to the new last element just added
if t(end) > a + 0.999999999*(b-a), break; end
end
q = (.5*tol/R)^0.25;
q = max(q,0.1);
q = min(q,4.0);
h = q*h;
if h<hmin, h=hmin; end
if h>hmax, h=hmax; end
if h>b-t(end), h=b-t(end); end % takes precedence over hmin
end
if n>=nmax
fprintf('error: nmax reached\n');







end