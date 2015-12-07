import sys
import itertools as it
import numpy as np
import math


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

def switchOptimization(sol, loop_limit) :
    fsol = open(sol, "r")
    array_of_solution_string = fsol.read().split("\n") #['3 4 6 6 8 2 2', '1 3 56 78 2 2 5 2 5 7 9 1 2', '12 3 6 8 8 1 3 5 7'], array_of_solution_string[0] = solution to 1.in 
    fsol.close()
    array_of_solution_int = []
    array_of_solution_score = []
    for i in range(0,621) :
        foriginal = open("original.out", "w")
        foriginal.write(array_of_solution_string[i])
        foriginal.close()
        array_of_solution_score.append(processTest(str(i+1) + ".in",  "original.out"))
        temp_int_array = []
        temp_int_array = map(int, array_of_solution_string[i].split()) 
        array_of_solution_int.append(temp_int_array)
 


    for i in range(1,622): #1, 2, 3, 4 .... 621
        print i
        for q in range(0, loop_limit) :
            if len(array_of_solution_int[i-1]) >= 6 :
                array_of_numbers = list(array_of_solution_int[i-1])
                copy1 = list(array_of_numbers)
                copy2 = list(array_of_numbers)
                copy3 = list(array_of_numbers)
                copy4 = list(array_of_numbers)
                copy5 = list(array_of_numbers)
                copy6 = list(array_of_numbers)
                copy7 = list(array_of_numbers)
                copy8 = list(array_of_numbers)
                copy9 = list(array_of_numbers)
                copy10 = list(array_of_numbers)
                copy11 = list(array_of_numbers)
                copy12 = list(array_of_numbers)
                copy13 = list(array_of_numbers)
                copy14 = list(array_of_numbers)
                copy15 = list(array_of_numbers)
                copy16 = list(array_of_numbers)
                copy17 = list(array_of_numbers)
                copy18 = list(array_of_numbers)
                copy19 = list(array_of_numbers)
                copy20 = list(array_of_numbers)
                copy21 = list(array_of_numbers)
                copy22 = list(array_of_numbers)
                copy23 = list(array_of_numbers)
                copy24 = list(array_of_numbers)
                copy25 = list(array_of_numbers)
                copy26 = list(array_of_numbers)
                copy27 = list(array_of_numbers)
                copy28 = list(array_of_numbers)
                copy29 = list(array_of_numbers)
                copy30 = list(array_of_numbers)
                copy31 = list(array_of_numbers)
                copy32 = list(array_of_numbers)
                copy33 = list(array_of_numbers)
                copy34 = list(array_of_numbers)
                copy35 = list(array_of_numbers)
                copy36 = list(array_of_numbers)

                a = np.random.randint(0,len(array_of_numbers)-1) #indices
                b = np.random.randint(0,len(array_of_numbers)-1)
                c = np.random.randint(0,len(array_of_numbers)-1)
                d = np.random.randint(0,len(array_of_numbers)-1)

                while (a==b or a==c or a==d or b==c or b==d or c==d) :
                    b = np.random.randint(0,len(array_of_numbers)-1)
                    c = np.random.randint(0,len(array_of_numbers)-1)
                    d = np.random.randint(0,len(array_of_numbers)-1)
                # while (a==c) :
                #     a = np.random.randint(0,len(array_of_numbers)-1) #indices
                #     c = np.random.randint(0,len(array_of_numbers)-1)
                # while (b==d) :
                #     b = np.random.randint(0,len(array_of_numbers)-1) #indices
                #     d = np.random.randint(0,len(array_of_numbers)-1)
                # while (b==c) :
                #     b = np.random.randint(0,len(array_of_numbers)-1) #indices
                #     c = np.random.randint(0,len(array_of_numbers)-1)
                # while (b==d) :
                #     b = np.random.randint(0,len(array_of_numbers)-1) #indices
                #     d = np.random.randint(0,len(array_of_numbers)-1)
                # while (c==d) :
                #     c = np.random.randint(0,len(array_of_numbers)-1) #indices
                #     d = np.random.randint(0,len(array_of_numbers)-1)
                
                
                #switch a and b
                placeholder = copy1[a]
                copy1[a] = copy1[b]
                copy1[b] = placeholder
                fintermediate = open("intermediate.out", "w")
                for j in copy1 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()
                score1 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #switch a and c
                placeholder = copy2[c]
                copy2[c] = copy2[a]
                copy2[a] = placeholder
                fintermediate = open("intermediate.out", "w")
                for j in copy2 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score2 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #switch a and d
                placeholder = copy3[c]
                copy3[c] = copy3[a]
                copy3[a] = placeholder
                fintermediate = open("intermediate.out", "w")
                for j in copy3 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score3 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE


                #switch b and c
                placeholder = copy4[b]
                copy4[b] = copy4[c]
                copy4[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy4 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score4 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #switch b and d
                placeholder = copy5[b]
                copy5[b] = copy5[c]
                copy5[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy5 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score5 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #switch c and d
                placeholder = copy6[b]
                copy6[b] = copy6[c]
                copy6[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy6 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score6 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a<b<c
                placeholder = copy7[a]
                copy7[a] = copy7[b]
                copy7[b] = copy7[c]
                copy7[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy7 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score7 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>b>c
                placeholder = copy8[c]
                copy8[c] = copy8[b]
                copy8[b] = copy8[a]
                copy8[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy8 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score8 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>c>b
                placeholder = copy9[b]
                copy9[b] = copy9[c]
                copy9[c] = copy9[a]
                copy9[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy9 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score9 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>a>c
                placeholder = copy10[c]
                copy10[c] = copy10[a]
                copy10[a] = copy10[b]
                copy10[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy10 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score10 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>c>a
                placeholder = copy11[a]
                copy11[a] = copy11[c]
                copy11[c] = copy11[b]
                copy11[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy11 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score11 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>a>b
                placeholder = copy12[b]
                copy12[b] = copy12[a]
                copy12[a] = copy12[c]
                copy12[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy12 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score12 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a<b<c<d
                placeholder = copy13[a]
                copy13[a] = copy13[b]
                copy13[b] = copy13[c]
                copy13[c] = copy13[d]
                copy13[d] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy13 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score13 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>b>c>d
                placeholder = copy14[d]
                copy14[d] = copy14[c]
                copy14[c] = copy14[b]
                copy14[b] = copy14[a]
                copy14[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy14 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score14 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>b>d>c
                placeholder = copy15[c]
                copy15[c] = copy15[d]
                copy15[d] = copy15[b]
                copy15[b] = copy15[a]
                copy15[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy15 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score15 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>c>b>d
                placeholder = copy16[d]
                copy16[d] = copy16[b]
                copy16[b] = copy16[c]
                copy16[c] = copy16[a]
                copy16[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy16 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score16 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>c>d>b
                placeholder = copy17[b]
                copy17[b] = copy17[d]
                copy17[d] = copy17[c]
                copy17[c] = copy17[a]
                copy17[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy17 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score17 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>d>b>c
                placeholder = copy18[c]
                copy18[c] = copy18[b]
                copy18[b] = copy18[d]
                copy18[d] = copy18[a]
                copy18[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy18 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score18 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #a>d>c>b
                placeholder = copy19[b]
                copy19[b] = copy19[c]
                copy19[c] = copy19[d]
                copy19[d] = copy19[a]
                copy19[a] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy19 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score19 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>a>c>d
                placeholder = copy20[d]
                copy20[d] = copy20[c]
                copy20[c] = copy20[a]
                copy20[a] = copy20[b]
                copy20[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy20 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score20 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>a>d>c
                placeholder = copy21[c]
                copy21[c] = copy21[d]
                copy21[d] = copy21[a]
                copy21[a] = copy21[b]
                copy21[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy21 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score21 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>c>a>d
                placeholder = copy22[d]
                copy22[d] = copy22[a]
                copy22[a] = copy22[c]
                copy22[c] = copy22[b]
                copy22[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy22 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score22 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>c>d>a
                placeholder = copy23[a]
                copy23[a] = copy23[d]
                copy23[d] = copy23[c]
                copy23[c] = copy23[b]
                copy23[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy23 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score23 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>d>a>c
                placeholder = copy24[c]
                copy24[c] = copy24[a]
                copy24[a] = copy24[d]
                copy24[d] = copy24[b]
                copy24[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy24 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score24 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #b>d>c>a
                placeholder = copy25[a]
                copy25[a] = copy25[c]
                copy25[c] = copy25[d]
                copy25[d] = copy25[b]
                copy25[b] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy25 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score25 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>a>b>d
                placeholder = copy26[d]
                copy26[d] = copy26[b]
                copy26[b] = copy26[a]
                copy26[a] = copy26[c]
                copy26[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy26 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score26 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>a>d>b
                placeholder = copy27[b]
                copy27[b] = copy27[d]
                copy27[d] = copy27[a]
                copy27[a] = copy27[c]
                copy27[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy27 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score27 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>b>a>d
                placeholder = copy28[d]
                copy28[d] = copy28[a]
                copy28[a] = copy28[b]
                copy28[b] = copy28[c]
                copy28[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy28 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score28 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>b>d>a
                placeholder = copy29[a]
                copy29[a] = copy29[d]
                copy29[d] = copy29[b]
                copy29[b] = copy29[c]
                copy29[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy29 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score29 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>d>a>b
                placeholder = copy30[b]
                copy30[b] = copy30[a]
                copy30[a] = copy30[d]
                copy30[d] = copy30[c]
                copy30[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy30 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score30 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #c>d>b>a
                placeholder = copy31[a]
                copy31[a] = copy31[b]
                copy31[b] = copy31[d]
                copy31[d] = copy31[c]
                copy31[c] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy31 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score31 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #d>a>b>c
                placeholder = copy32[c]
                copy32[c] = copy32[b]
                copy32[b] = copy32[a]
                copy32[a] = copy32[d]
                copy32[d] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy32 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score32 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #d>a>c>b
                placeholder = copy33[b]
                copy33[b] = copy33[c]
                copy33[c] = copy33[a]
                copy33[a] = copy33[d]
                copy33[d] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy33 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score33 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #d>b>a>c
                placeholder = copy34[c]
                copy34[c] = copy34[a]
                copy34[a] = copy34[b]
                copy34[b] = copy34[d]
                copy34[d] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy34 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score34 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #d>b>c>a
                placeholder = copy35[a]
                copy35[a] = copy35[c]
                copy35[c] = copy35[b]
                copy35[b] = copy35[d]
                copy35[d] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy35 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score35 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                #d>c>a>b
                placeholder = copy36[b]
                copy36[b] = copy36[a]
                copy36[a] = copy36[c]
                copy36[c] = copy36[d]
                copy36[d] = placeholder

                fintermediate = open("intermediate.out", "w")
                for j in copy36 :
                    fintermediate.write(str(j) + " ")
                fintermediate.close()

                score36 = processTest(str(i) + ".in" ,"intermediate.out") #NEW SCORE

                
                max_score = max(score1, score2, score3, score4, score5, score6, score7, score8, score9, score11, score12, score13, score14, score15, score16, score17, score18, score19, score20, score21, score22, score23, score24, score25, score26, score27, score28, score29, score30, score31, score32, score33, score34, score35, score36)

                if max_score > array_of_solution_score[i-1] :
                    print "increase" + " " + str(max_score - array_of_solution_score[i-1])
                    array_of_solution_score[i-1] = max_score
                    if score1 == max_score:
                        array_of_solution_int[i-1] = list(copy1)
                    elif score2 == max_score:
                        array_of_solution_int[i-1] = list(copy2)
                    elif score3 == max_score:
                        array_of_solution_int[i-1] = list(copy3)
                    elif score4 == max_score:
                        array_of_solution_int[i-1] = list(copy4)
                    elif score5 == max_score:
                        array_of_solution_int[i-1] = list(copy5)
                    elif score6 == max_score:
                        array_of_solution_int[i-1] = list(copy6)
                    elif score7 == max_score:
                        array_of_solution_int[i-1] = list(copy7)
                    elif score8 == max_score:
                        array_of_solution_int[i-1] = list(copy8)
                    elif score9 == max_score:
                        array_of_solution_int[i-1] = list(copy9)
                    elif score10 == max_score:
                        array_of_solution_int[i-1] = list(copy10)
                    elif score11 == max_score:
                        array_of_solution_int[i-1] = list(copy11)
                    elif score12 == max_score:
                        array_of_solution_int[i-1] = list(copy12)
                    elif score13 == max_score:
                        array_of_solution_int[i-1] = list(copy13)
                    elif score14 == max_score:
                        array_of_solution_int[i-1] = list(copy14)
                    elif score15 == max_score:
                        array_of_solution_int[i-1] = list(copy15)
                    elif score16 == max_score:
                        array_of_solution_int[i-1] = list(copy16)
                    elif score17 == max_score:
                        array_of_solution_int[i-1] = list(copy17)
                    elif score18 == max_score:
                        array_of_solution_int[i-1] = list(copy18)
                    elif score19 == max_score:
                        array_of_solution_int[i-1] = list(copy19)
                    elif score20 == max_score:
                        array_of_solution_int[i-1] = list(copy20)
                    elif score21 == max_score:
                        array_of_solution_int[i-1] = list(copy21)
                    elif score22 == max_score:
                        array_of_solution_int[i-1] = list(copy22)
                    elif score23 == max_score:
                        array_of_solution_int[i-1] = list(copy23)
                    elif score24 == max_score:
                        array_of_solution_int[i-1] = list(copy24)
                    elif score25 == max_score:
                        array_of_solution_int[i-1] = list(copy25)
                    elif score26 == max_score:
                        array_of_solution_int[i-1] = list(copy26)
                    elif score27 == max_score:
                        array_of_solution_int[i-1] = list(copy27)
                    elif score28 == max_score:
                        array_of_solution_int[i-1] = list(copy28)
                    elif score29 == max_score:
                        array_of_solution_int[i-1] = list(copy29)
                    elif score30 == max_score:
                        array_of_solution_int[i-1] = list(copy30)
                    elif score31 == max_score:
                        array_of_solution_int[i-1] = list(copy31)
                    elif score32 == max_score:
                        array_of_solution_int[i-1] = list(copy32)
                    elif score33 == max_score:
                        array_of_solution_int[i-1] = list(copy33)
                    elif score34 == max_score:
                        array_of_solution_int[i-1] = list(copy34)
                    elif score35 == max_score:
                        array_of_solution_int[i-1] = list(copy35)
                    elif score36 == max_score:
                        array_of_solution_int[i-1] = list(copy36)

                
    fsol = open(sol, "w")
    for i in array_of_solution_int :
        for j in range(0, len(i)) :
            fsol.write(str(i[j]) + " ")    
        fsol.write("\n")
    fsol.close()   

  



def main(argv):
    switchOptimization("output.out", 5)
    
if __name__ == '__main__':
    main(sys.argv[1:])