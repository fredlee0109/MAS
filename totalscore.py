import sys


def compare(sol):
    bk = open(sol, "r") #open Brian's solution
    final = 0

    for i in range(1, 622):
        t1 = open('t1.out', 'w')

        line1 = bk.readline()

        t1.write(line1)
        t1.close()

        inst = str(i) + ".in"
        score1 = processTest(inst, "t1.out")

        final += score1

    print final 





def processTest(inst, sol):  #this code is from scorer_single.py
    fin = open(inst, "r") 
    N = int(fin.readline().split()[0])
    d = [[0 for j in range(N)] for i in range(N)]
    for i in xrange(N):
        d[i] = map(int, fin.readline().split())
    fin = open(sol, "r")
    ans = map(lambda x: (int(x) - 1), fin.readline().split())

    count = 0.0
    for i in xrange(N):
        for j in xrange(i + 1, N):

            if d[ans[i]][ans[j]] == 1:
                count += 1
    return count

def main(argv):
    compare(argv[0])


if __name__ == '__main__':
    main(sys.argv[1:])