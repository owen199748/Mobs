package cn.rpgmc.mobs.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class LoggerListener implements Filter {
	public LoggerListener() {
	}
	@Override
	public boolean isLoggable(LogRecord record) {
		if (record.getThrown() != null) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		record.getThrown().printStackTrace(pw);
			String str = sw.toString();
			if (str.indexOf("cn.rpgmc") != -1)
				ErrorReport.report(str);

		}

		return true;
	}

	public static String toString(Throwable t) {
		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			String str = sw.toString();
			return str;

		}
		return null;
	}

}
