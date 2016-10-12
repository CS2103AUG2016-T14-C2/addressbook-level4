package seedu.address.commons.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.model.AddressBook;
import seedu.address.model.SavvyTasker;
import seedu.address.storage.XmlSerializableSavvyTasker;
import seedu.address.testutil.SavvyTaskerBuilder;
import seedu.address.testutil.TestUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class XmlUtilTest {

    private static final String TEST_DATA_FOLDER = FileUtil.getPath("src/test/data/XmlUtilTest/");
    private static final File EMPTY_FILE = new File(TEST_DATA_FOLDER + "empty.xml");
    private static final File MISSING_FILE = new File(TEST_DATA_FOLDER + "missing.xml");
    private static final File VALID_FILE = new File(TEST_DATA_FOLDER + "validSavvyTasker.xml");
    private static final File TEMP_FILE = new File(TestUtil.getFilePathInSandboxFolder("tempSavvyTasker.xml"));

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getDataFromFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(null, AddressBook.class);
    }

    @Test
    public void getDataFromFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.getDataFromFile(VALID_FILE, null);
    }

    @Test
    public void getDataFromFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.getDataFromFile(MISSING_FILE, AddressBook.class);
    }

    @Test
    public void getDataFromFile_emptyFile_DataFormatMismatchException() throws Exception {
        thrown.expect(JAXBException.class);
        XmlUtil.getDataFromFile(EMPTY_FILE, AddressBook.class);
    }

    @Test
    public void getDataFromFile_validFile_validResult() throws Exception {
        XmlSerializableSavvyTasker dataFromFile = XmlUtil.getDataFromFile(VALID_FILE, XmlSerializableSavvyTasker.class);
        assertEquals(3, dataFromFile.getReadOnlyListOfTasks().size());
    }

    @Test
    public void saveDataToFile_nullFile_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(null, new AddressBook());
    }

    @Test
    public void saveDataToFile_nullClass_AssertionError() throws Exception {
        thrown.expect(AssertionError.class);
        XmlUtil.saveDataToFile(VALID_FILE, null);
    }

    @Test
    public void saveDataToFile_missingFile_FileNotFoundException() throws Exception {
        thrown.expect(FileNotFoundException.class);
        XmlUtil.saveDataToFile(MISSING_FILE, new AddressBook());
    }

    @Test
    public void saveDataToFile_validFile_dataSaved() throws Exception {
        TEMP_FILE.createNewFile();
        XmlSerializableSavvyTasker dataToWrite = new XmlSerializableSavvyTasker(new SavvyTasker());
        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        XmlSerializableSavvyTasker dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableSavvyTasker.class);
        assertEquals((new SavvyTasker(dataToWrite)).toString(),(new SavvyTasker(dataFromFile)).toString());
        //TODO: use equality instead of string comparisons

        SavvyTaskerBuilder builder = new SavvyTaskerBuilder(new SavvyTasker());
        dataToWrite = new XmlSerializableSavvyTasker(builder.withTask(TestUtil.generateSampleTaskData().get(0)).build());

        XmlUtil.saveDataToFile(TEMP_FILE, dataToWrite);
        dataFromFile = XmlUtil.getDataFromFile(TEMP_FILE, XmlSerializableSavvyTasker.class);
        assertEquals((new SavvyTasker(dataToWrite)).toString(),(new SavvyTasker(dataFromFile)).toString());
    }
}
