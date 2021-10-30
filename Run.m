f = @(x) x - exp(cos([1:4]'*sum(x)));
p1 = newton(f,@JJ,[2.5;2;1.4;.9],1.0e-12);
format long
disp(p1);

