def go(val):
    global ans
    if len(str(val)) == n:
        if val%3 == 0: ans += 1
        return
    for i in range(3):
        go(val*10 + i)

n = int(input())
ans = 0
go(1)
go(2)
print(ans)