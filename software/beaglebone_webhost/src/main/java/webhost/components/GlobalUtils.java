package webhost.components;

import org.apache.commons.logging.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import webhost.enums.WebHostExceptionType;
import webhost.http_wrappers.UpdateExceptionWrapper;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class GlobalUtils {
    
    /* ************************************************* START INSTANCE VARIABLES ************************************************** */
    
    private final ObjectMapper oMapper;
    
    @Value("${info.build.name}")
    private String build_name;
    @Value("${info.build.version}")
    private String build_version;
    @Value("${info.build.timestamp}")
    private String build_timestamp;
    
    /**
     * System file separator {@code String} for this application
     */
    private final static String fileSep = File.separator;
    
    /**
     * Absolute path to resource directory used by this application
     */
    /* live directory */
//    private final static String resourceLoc = "";
    /* griffens directory */
    private final static String resourceLoc = "J:" + fileSep + ".docs"+ fileSep + "academics" + fileSep + "iowa_state" + fileSep + "senior_design" + fileSep + "git" + fileSep + "software" + fileSep + "beaglebone_webhost" + fileSep + "src" + fileSep + "main" + fileSep + "resources" + fileSep;
    /* tcweil directory */
//    private final static String resourceLoc = "";
    
    /**
     * Absolute path to output directory of this application
     */
    /* live directory */
//    private final static String outputLoc = "";
    /* griffens directory */
    private final static String outputLoc = "J:" + fileSep + ".docs"+ fileSep + "academics" + fileSep + "iowa_state" + fileSep + "senior_design" + fileSep + "git" + fileSep + "software" + fileSep + "beaglebone_webhost" + fileSep + "output" + fileSep;
    /* tcweil directory */
//    private final static String outputLoc = "";
    
    /* ************************************************** END INSTANCE VARIABLES *************************************************** */
    
    /* **************************************************** START CONSTRUCTORS ***************************************************** */
    
    /**
     * Constructs a {@code GlobalUtils}
     */
    @Autowired
    public GlobalUtils(ObjectMapper oMapper) {
    
        if (oMapper != null) { this.oMapper = oMapper; }
        else { this.oMapper = new ObjectMapper(); }
        this.oMapper.findAndRegisterModules();
    }
    
    /* ***************************************************** END CONSTRUCTORS ****************************************************** */
    
    /* *************************************************** START GETTERS/SETTERS *************************************************** */
    
    /**
     * Get {@code oMapper}
     * @return
     *      {@code oMapper}
     */
    public ObjectMapper getOMapper() { return this.oMapper; }
    
    /**
     * Get {@code build_name}
     * @return
     *      {@code build_name}
     */
    public String getBuild_name() { return build_name; }
    /**
     * Get {@code build_version}
     * @return
     *      {@code build_version}
     */
    public String getBuild_version() { return build_version; }
    /**
     * Get {@code build_timestamp}
     * @return
     *      {@code build_timestamp}
     */
    public String getBuild_timestamp() { return build_timestamp; }
    
    /**
     * Get {@code fileSep}
     * @return
     *      {@code fileSep}
     */
    public static String getFileSep() { return fileSep; }
    
    /**
     * Get {@code resourceLoc}
     * @return
     *      {@code resourceLoc}
     */
    public static String getResourceLoc() { return resourceLoc; }
    
    /**
     * Get {@code outputLoc}
     * @return
     *      {@code outputLoc}
     */
    public static String getOutputLoc() { return outputLoc; }
    
    /**
     * Returns a {@code String} format of the current {@code Calendar} instance
     * @return
     *      {@code String} in the format: {@code MM_DD_YYYY}
     */
    public static String getCalendarDate() {
        
        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Chicago"));
        return date.format(DateTimeFormatter.ofPattern("MM_dd_yyyy"));
    }
    
    /**
     * Returns the current {@code LocalDateTime} instance
     * @return
     *      {@code LocalDateTime} instance
     */
    public static LocalDateTime getDateTime() { return LocalDateTime.now(ZoneId.of("America/Chicago")); }
    
    /**
     * Returns the date and time (24-base) in a {@code String} format based off of the current {@code LocalDateTime} instance
     * @return
     *      {@code String} in the format: {@code MM_dd_yyyy-HH_mm}
     */
    public static String getDateTime24() {
        
        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Chicago"));
        return getCalendarDate() + "-"
                       + date.format(DateTimeFormatter.ofPattern("HH_mm"));
    }
    
    /**
     * Returns the date and time (12-base) in a {@code String} format based off of the current {@code LocalDateTime} instance
     * @return
     *      {@code String} in the format: {@code MM_dd_yyyy-hh_mm_AM/PM}
     */
    public static String getDateTime12() {
        
        LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Chicago"));
        return getCalendarDate() + "-" + date.format(DateTimeFormatter.ofPattern("hh_mm_a"));
    }
    
    public static String convertDateTime_string(LocalDateTime date) {
        
        return date.format(DateTimeFormatter.ofPattern("MM_dd_yyyy")) + "-"
                       + date.format(DateTimeFormatter.ofPattern("HH_mm"));
    }
    
    /* **************************************************** END GETTERS/SETTERS **************************************************** */
    
    /* ***************************************************** START MISC METHODS **************************************************** */
    
    /**
     * Cleans a directory of all child {@code Files} and subdirectories. Also deletes inputted File
     * @param toClean
     *      {@code File} (where listFiles() is nonnull) to clean
     * @return
     *      {@code true} if toClean.delete() was successful, {@code false} otherwise
     */
    public static Boolean cleanDirectory(File toClean) {
        
        File[] allContents = toClean.listFiles();
        if (allContents != null) {
            
            for (File file : allContents) { cleanDirectory(file); }
        }
        return toClean.delete();
    }
    
    /**
     * Returns a {@code String} in the {@code JSON} format, read from a .json file
     * @param jsonFile
     *      {@code File} to read from
     * @return
     *      {@code JSON} {@code String} of data read from {@cpde jsonFile}
     * @throws IOException
     */
    public static String getJsonFromFile(File jsonFile) throws IOException {
        
        InputStream inStream = new FileInputStream(jsonFile);
        byte[] byteArr = IOUtils.toByteArray(inStream);
        String result = new String(byteArr);
        result = result.replaceAll("[\n\r ]", "");
        
        return result.trim();
    }
    
    /**
     * Writes a {@code String} to a {@code File}
     * @param toOutput
     * 		{@code String} to output
     * @param dirPath
     * 		{@code File} location for the directory to output to
     * @param fileName
     * 		{@code File} name for the outputted {@code String}
     * @param fileType
     * 		{@code File} type to output (json, txt, etc)
     * @return
     * 		Absolute file path to the generated file
     * @throws IOException
     * 		Throws if:
     * 		<br>
     * 		<ol type="1">
     * 			<li>{@code File} trying to write to cannot be created</li>
     * 			<li>{@code File} trying to write to is not found</li>
     * 			<li>Other {@code IOException} has occurred</li>
     * 		</ol>
     */
    public static String outputString(String toOutput, String dirPath, String fileName, String fileType) throws IOException {
        
        File out;
        if (fileType.startsWith(".")) { out = new File(dirPath, fileName + fileType); }
        else { out = new File(dirPath, fileName + "." + fileType); }
        out.createNewFile();
        
        FileOutputStream outStream = new FileOutputStream(out);
        outStream.write(toOutput.getBytes());
        outStream.close();
        
        String result = out.getAbsolutePath();
        return result;
    }
    
    /**
     * Handles successful executions by {@code @Services}
     * @param log
     *      {@code Log} to write messages to
     * @param logS
     * 		{@code String} to log
     * @param s
     * 		{@code String} to return
     * @return
     * 		{@code String}
     */
    public static String successHandler(Log log, String logS, String s) {
    
        log.info(logS);
        return s;
    }
    
    /**
     * Called when a thrown exception is caught
     * <br>
     * Creates an {@code ExceptionUpdateWrapper} so if an error is thrown, information is returned to the frontend so a bug report can be submitted
     * @param eClass
     * 		{@code Class} of the {@code Exception} thrown
     * @param eType
     * 		{@code WebClientExceptionType} of the exception
     * @param eCode
     * 		Status code to display and handle on the error webpage
     * @param eMessage
     * 		{@code String} to display on the error webpage. Used for {@code Exception} diagnosis
     * @return
     * 		@see UpdateExceptionWrapper
     */
    public static UpdateExceptionWrapper generateExceptionMap(Class<?> eClass, WebHostExceptionType eType, int eCode, String eMessage) {
        
        List<Class<?>> eClasses = new ArrayList<>();
        List<WebHostExceptionType> eTypes = new ArrayList<>();
        List<Integer> eCodes = new ArrayList<>();
        List<String> eMessages = new ArrayList<>();
        
        eClasses.add(eClass);
        eTypes.add(eType);
        eCodes.add(eCode);
        eMessages.add(eMessage);
        
        UpdateExceptionWrapper wrapper = new UpdateExceptionWrapper();
        wrapper.setExceptionClass(eClass);
        wrapper.setExceptionType(eType);
        wrapper.setExceptionStatus(eCode);
        wrapper.setExceptionMessage(eMessage);
        
        return wrapper;
    }
    
    /* ****************************************************** END MISC METHODS ***************************************************** */
    
    /* *************************************************** START PRIVATE METHODS *************************************************** */
    
    
    
    /* **************************************************** END PRIVATE METHODS **************************************************** */
    
    /* ****************************************************** END GLOBAL UTILS ***************************************************** */
}
