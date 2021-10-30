function p = newton(f,J,p0,tol)
for i=1:50
p = p0 - J(p0)\f(p0);
if norm(p-p0)<tol, break; end
p0=p;
end

