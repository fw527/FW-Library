function J = JJ(x)
J = eye(4); % 4x4 identity matrix
u = sum(x);


for i=1:4
for j=1:4
df =  i*sin(i*u)*exp(cos(i*u));
J(i,j) = J(i,j)+ df; % HAVE TO ADD J ij =d fi /d xj HERE
end
end