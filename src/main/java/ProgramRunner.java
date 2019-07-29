import com.spectory.linux.file.manager.setup.EnvConf;
import com.spectory.linux.file.manager.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRunner{

    private static final Logger logger = LoggerFactory.getLogger(ProgramRunner.class);
    private static String workingDir = EnvConf.getProperty("test.env");
//    private static final String LOG_FOLDER = EnvConf.getProperty("test_output.logs.folder");

    public static void main(String[] args) throws IOException {


        logger.info("Loading setup for File Manager Tester");
        System.out.println("**** PLEASE ENTER 'help' IN ORDER TO SEE COMMANDS SUPPORTED ****");
        System.out.print("shell>");
        String commandLine;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        commandLine = console.readLine();
        if (commandLine.equals("help")) {
            System.out.println("\n***WELCOME TO MY SHELL HELP***" +
                    "\n Copyright @ Suprotik Dey" +
                    "\n -Use the shell at your own risk..." +
                    "\n List of Commands supported:" +
                    "\n> cd" +
                    "\n> ls" +
                    "\n> mkfile use it as following [mkfile <name_of_the_file>]" +
                    "\n> mkdir use it as following [mkdir <name_of_the_folder>]" +
                    "\n> press 'exit' in any phase in order to exit the super shell command executor");

        }

        while (!commandLine.equals("exit")) {

            switch (commandLine.trim()) {
                case "ls":
                    FileUtil.lsCommand(workingDir);
                    break;

                case "mkdir":
                    System.out.println("Please insert the command by the following convention 'mkdir' <name_of_the_desired_folder_name>");
                    commandLine = console.readLine();
                    FileUtil.mkdirCommand(workingDir + "/" + commandLine.split(" ")[1]);
                    break;

                case "mkfile":
                    System.out.println("Please insert the command by the following convention 'mkfile' <name_of_the_desired_file_name>");
                    commandLine = console.readLine();
                    FileUtil.mkfileCommand(workingDir + "/" + commandLine.split(" ")[1]);
                    break;

                case "cd":
                    System.out.println("Please insert the command by the following convention 'cd' <name_of_the_desired_folder_name>");
                    commandLine = console.readLine();
                    settingWorkingDir(FileUtil.cdCommand(workingDir + "/" + commandLine.split(" ")[1]));
                    break;

                default:
                    System.out.println("> insert your next command\n>");
            }
            System.out.print("shell>");
            commandLine = console.readLine();

        }

    }

    private static void settingWorkingDir(String s) {
        workingDir = s;
    }

}
