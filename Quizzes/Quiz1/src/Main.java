import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) {
		String inputFile = args[0];
		FileOutput.writeToFile("output.txt","", false, false);

		String[] input = FileInput.readFile(inputFile, true , true);
		String line;
		ArrayList<Integer> sortList;
		ArrayList<String> outList;
		int k;
		int temp;
		String intString;
		if (input != null) {
			for(int i = 0; i < input.length; i++){
				line = input[i];
				int upLimNum;
				String output;
				switch (line) {
					case "Armstrong numbers up to:":
						output = "Armstrong numbers up to " + input[i+1] + ":";
						FileOutput.writeToFile("output.txt", output, true, true);
						upLimNum = Integer.parseInt(input[i+1]);
						Armstrong arm = new Armstrong();
						arm.setUpperLimitNumber(upLimNum);
						arm.generateArmstrong();
						break;
					case "Emirp numbers up to:":
						output = "Emirp numbers up to " + input[i+1] + ":";
						FileOutput.writeToFile("output.txt", output, true, true);
						upLimNum = Integer.parseInt(input[i+1]);
						Emirp emi = new Emirp();
						emi.setUpperLimitNumber(upLimNum);
						emi.generateEmirp();
						break;
					case "Abundant numbers up to:":
						output = "Abundant numbers up to " + input[i+1] + ":";
						FileOutput.writeToFile("output.txt", output, true, true);
						upLimNum = Integer.parseInt(input[i+1]);
						Abundant abu = new Abundant();
						abu.setUpperLimitNumber(upLimNum);
						abu.generateAbundant();
						break;
					case "Ascending order sorting:":
						FileOutput.writeToFile("output.txt", line, true, true);
						sortList = new ArrayList<>();
						k = i + 1;
						while (!isCommand(input[k])) {
							outList = new ArrayList<>();
							temp = Integer.parseInt(input[k]);
							sortList.add(temp);
							Collections.sort(sortList);
							for(int num : sortList) {
								intString = String.valueOf(num);
								outList.add(intString);
							}
							output = String.join(" ", outList);
							FileOutput.writeToFile("output.txt", output, true, true);
							k++;
						}
						FileOutput.writeToFile("output.txt", "\n", true, false);
						break;
					case "Descending order sorting:":
						FileOutput.writeToFile("output.txt", line, true, true);
						sortList = new ArrayList<>();
						k = i + 1;
						while (!isCommand(input[k])) {
							outList = new ArrayList<>();
							temp = Integer.parseInt(input[k]);
							sortList.add(temp);
							sortList.sort(Collections.reverseOrder());
							for(int num : sortList) {
								intString = String.valueOf(num);
								outList.add(intString);
							}
							output = String.join(" ", outList);
							FileOutput.writeToFile("output.txt", output, true, true);
							k++;
						}
						FileOutput.writeToFile("output.txt", "\n", true, false);
						break;
					case "Exit":
						FileOutput.writeToFile("output.txt", "Finished...", true, true);
						System.exit(0);
				}
			}
		}
	}


	static boolean isCommand(String line) {
		int intVal;
		try {
			intVal = Integer.parseInt(line);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}
}






