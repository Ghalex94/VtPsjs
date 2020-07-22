package clases;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExportarExcel {
    
    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("Hoja 1");
                hoja.setDisplayGridlines(false);
                String cabecera[] = {"TIPO DE DOCUMENTO","TIPO DE COMPROBANTE", "SERIE","CORRELATIVO",	"FECHA DE VENTA", "TIPO DOC.", "N° DOCUMENTO","NOMBRES O RAZÓN SOCIAL","TOTAL","ESTADO"};
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(cabecera[c]);
                        }
                    }
                }
                int filaInicio = 1;
                DecimalFormat df = new DecimalFormat("#.00");
                float totalGeneral = 0;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    /*
                    if(filaInicio == t.getRowCount()) {
                    	Cell celda = fila.createCell(8);
                    	celda.setCellValue(df.format(totalGeneral));
                    	break;
                    }*/
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if(c==8) {
                        	float total = Float.parseFloat(t.getValueAt(f, c).toString());
                        	totalGeneral+=total;
                        	celda.setCellValue(df.format(total));
                        	continue;
                        }
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        }
                    }
                }
                for (int c = 0; c < t.getColumnCount(); c++) {
                	hoja.autoSizeColumn(c);
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
}
