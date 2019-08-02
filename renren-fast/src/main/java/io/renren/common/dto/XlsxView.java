package io.renren.common.dto;

import cn.hutool.core.util.ReflectUtil;
import io.renren.common.utils.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Title: XlsxView
 * </p>
 * <p>
 * Description: 一个xlsx导出view
 * </p>
 * 
 * @auth xiaolc
 * @date 2018年4月16日 上午10:18:24
 */
public class XlsxView extends AbstractXlsxStreamingView {

	public static final String TIME_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";

	protected String name;

	protected List<Map<String, Object>> title;

	protected Iterable<?> values;
	
	public XlsxView(String name, List<Map<String, Object>> title, Iterable<?> values) {
		this.name = name;
		this.title = title;
		this.values = values;
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
									  Workbook workbook, HttpServletRequest request,
									  HttpServletResponse response) throws Exception {
		SXSSFWorkbook sxssWb = (SXSSFWorkbook) workbook;
		if(name == null){
			name = "导出";
		}
		Sheet sheet = sxssWb.createSheet(name);
		applyTitle(sheet);
		applyBody(sheet);
		applyName(response);

//		String reqCharset = request.getCharacterEncoding();
		/*根据request的getCharacterEncoding得到请求时的编码*/
//		filename = new String(filename.getBytes(reqCharset), "ISO8859-1");
//		resp.setCharacterEncoding(reqCharset);
//		// 若想下载时自动填好文件名，则需要设置响应头的"Content-disposition"
//		resp.setHeader("Content-disposition", "attachment;filename=" + filename);


	};

	protected void applyBody(Sheet sheet) {
		if(values == null){
			return;
		}
		Iterator<?> vlt = values.iterator();
		int rowIndex = 1;
		while (vlt.hasNext()) {
			Row row = sheet.createRow(rowIndex++);
			Object vl = vlt.next();
			int type = getType(vl);
			if (type == -1) {
				continue;
			}
			applyValueRow(row, vl, type);
		}

	}

	@SuppressWarnings("unchecked")
	protected void applyValueRow(Row row, Object o, int type) {
		if (type == 1) {
			Map<String, Object> vl = (Map<String, Object>) o;
			for (int i = 0; i < title.size(); i++) {
				Cell cell = row.createCell(i);
				applyCellStyle(cell, i);
				Object v = vl.get(String.valueOf(title
						.get(i).get("index")));
				applyValueFormat(cell,v,(String)title.get(i).get("format"));
			}
		} else if (type == 2) {
			List<Object> vl = (List<Object>) o;
			for (int i = 0; i < title.size(); i++) {
				Cell cell = row.createCell(i);
				applyCellStyle(cell, i);
				Object v = vl.get(i);
				applyValueFormat(cell,v,(String)title.get(i).get("format"));
			}
		} else if (type == 3) {
			for (int i = 0; i < title.size(); i++) {
				Cell cell = row.createCell(i);
				applyCellStyle(cell, i);
				Object v = Array.get(o, i);
				applyValueFormat(cell,v,(String)title.get(i).get("format"));
			}
		} else if (type == 0) {
			for (int i = 0; i < title.size(); i++) {
				Cell cell = row.createCell(i);
				applyCellStyle(cell, i);
				Object v = ReflectUtil.getFieldValue(o,
						String.valueOf(title.get(i).get("index")));
				applyValueFormat(cell,v,(String)title.get(i).get("format"));
			}
		}
	}

	protected void applyValueFormat(Cell cell,Object value,String format){
		String rowValue = null;
		if(value == null){
			rowValue = "";
		}else{
			if(value instanceof Date){
				rowValue = DateUtils.formatDate((Date)value, format == null?TIME_FORMAT_NORMAL:format);
			}
			if(value instanceof Number && format!=null){
				rowValue = new DecimalFormat(format).format((Number)value);
			}
			if(rowValue == null){
				rowValue = String.valueOf(value);
			}
		}
		cell.setCellValue(rowValue);
	}
	
	protected void applyCellStyle(Cell cell, int i) {
		if (title.get(i).get("type") != null) {
			cell.setCellType((Integer) title.get(i).get("type"));
		} else {
			cell.setCellType(SXSSFCell.CELL_TYPE_STRING);
		}
	}

	protected void applyTitle(Sheet sheet) {
		Row row = sheet.createRow(0);
		for (int i = 0; i < title.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellType(SXSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(String.valueOf(title.get(i).get("name")));
		}
	}
	
	protected void applyName(HttpServletResponse response){
		String filename = null;
		try {
			filename = new String((name+".xlsx").getBytes("UTF-8"), "ISO8859-1");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + filename);
		} catch (UnsupportedEncodingException e) {
		}   
	}

	protected int getType(Object o) {
		if (o == null) {
			return -1;
		}
		if (o instanceof Map) {
			return 1;
		}
		if (o instanceof List) {
			return 2;
		}
		if (o.getClass().isArray()) {
			return 3;
		}
		return 0;
	}
	
	public void write(OutputStream out) throws Exception{
		Workbook workbook = createWorkbook(null, null);
		buildExcelDocument(null, workbook, null, null);
		workbook.write(out);
		if (workbook instanceof Closeable) {
			((Closeable) workbook).close();
		}
	}
	
	public void initSheet(Workbook workbook, String name){
        Sheet sheet = workbook.createSheet(name);
        applyTitle(sheet);
        applyBody(sheet);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
