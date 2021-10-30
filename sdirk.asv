function [t,y] = sdirk(f,J,a,b,y0,h,hmin,hmax,tol)
t(1) = a;
y = y0;
h = min(h,b-a);
A = [  1/4,       0,      0,  0,  0;
1/2,   1/4,     0,    0,   0;
17/50, -1/25,  1/4,   0,   0;
371/1360, -137/2720,  15/544,   1/4,  0;
25/24,  -49/48, 125/16,-85/12, 1/4];
bb = [  25/24, -49/48, 125/16, -85/12, 1/4]';
b1 = [  59/48, -17/96, 225/32, -85/12,  0]';
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
ah = h*A(i,i);
g = @(k) k'-f(z+ah*k');  % you figure out what goes here (involves f)
dg = @(k) 1-J(z+ah*k')*ah; % and here.. (involves J)
kk(:,i) = newton(g,dg,f(y0),1.0e-12); % this line becomes more complicated in sdirk.m
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