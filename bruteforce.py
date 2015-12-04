import sys
import itertools as it

def expander(n, inst):
	
    scan = it.permutations(range(1, n+1))
    max_count = 0.0
    limit = 0

    placeholder = []
    for i in scan :
    	limit = limit + 1
    	c = [int(e) for e in i]
    	fwrite = open("foo.out","w") 

        for j in c:
            fwrite.write(str(j) + " ")        
        
        fwrite.close()
        count = processTest(inst, "foo.out")

        if count > max_count:
            max_count = count
            placeholder = c
        if limit == 1000:
        	return placeholder
        
    return placeholder       

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
	# return "solution value is %d" % count
	return count

def main(argv):
    
    print expander(int(argv[0]), argv[1])


if __name__ == '__main__':
	main(sys.argv[1:])