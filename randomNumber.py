def make(n):
	file_output = open(os.environ['PATH'], 'w')
	count = int(raw_input())
	input_list = []
	while count > 0:
		number = int(raw_input())
		input_list.append(number)
		count--

	result = sum(input_ist)
	file_output.write(str(result) + "\n")
	file_output.close()


