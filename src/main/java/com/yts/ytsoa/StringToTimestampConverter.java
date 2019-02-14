package com.yts.ytsoa;

/**
 * @author: LD
 * @date:
 * @description:
 */
//@Configuration
public class StringToTimestampConverter {
}
//implements Converter<String,Timestamp> {
//
//    private final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @Override
//    public Timestamp convert(String text) {
//        Timestamp timestamp=null;
//        if(StringUtils.hasText(text)){
//            text = text.trim();
//            boolean isLong = true;
//            try {
//                long millisecond = Long.parseLong(text);
//                timestamp=new Timestamp(millisecond);
//            }catch(Exception e){
//                isLong = false;
//            }
//            if(!isLong) {
//                try {
//                    SimpleDateFormat sdf = new SimpleDateFormat(FormatUtils.getFormatter(text.length()));
//                    String format = defaultDateFormat.format(sdf.parse(text));
//                    System.out.println(format);
//                } catch (ParseException var3) {
//                    throw new IllegalArgumentException("Could not parse date: " + var3.getMessage(), var3);
//                }
//            }
//        }
//        return timestamp;
//    }
//}
