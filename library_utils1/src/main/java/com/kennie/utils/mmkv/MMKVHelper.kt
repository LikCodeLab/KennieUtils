package com.kennie.utils.mmkv

import com.tencent.mmkv.MMKV

object MMKVHelper {

    //获取kv实例
    val kv = MMKV.defaultMMKV()

    /**
     * @param dataType 保存数据的类型
     * @param dataKey 保存数据的key
     * @param dataValue 保存数据的value
     * */
    @JvmStatic
    fun saveValue(dataType: String, dataKey: String, dataValue: String) {
        when (dataType) {
            MMKVDataType.BOOLEAN.toString() -> kv.encode(dataKey, dataValue.toBoolean())
            MMKVDataType.INT.toString() -> kv.encode(dataKey, dataValue.toInt())
            MMKVDataType.LONG.toString() -> kv.encode(dataKey, dataValue.toLong())
            MMKVDataType.FLOAT.toString() -> kv.encode(dataKey, dataValue.toFloat())
            MMKVDataType.DOUBLE.toString() -> kv.encode(dataKey, dataValue.toDouble())
            MMKVDataType.STRING.toString() -> kv.encode(dataKey, dataValue)
            MMKVDataType.ENTITY.toString() -> kv.encode(dataKey, dataValue)
        }
    }

    /**
     * @param dataType 数据类型
     *        boolean 布尔类型
     *        int     int
     *        long    长整形
     *        float   float
     *        double  double
     *        string  字符串
     *        entity  实体类  将实体类转成json字符串再进行保存
     * @param dataKey  保存的key
     * */
    fun getValue(dataType: String, dataKey: String): String? {
        return when (dataType) {
            MMKVDataType.BOOLEAN.toString() -> "${kv.decodeBool(dataKey)}"
            MMKVDataType.INT.toString() -> "${kv.decodeInt(dataKey)}"
            MMKVDataType.LONG.toString() -> "${kv.decodeLong(dataKey)}"
            MMKVDataType.FLOAT.toString() -> "${kv.decodeFloat(dataKey)}"
            MMKVDataType.DOUBLE.toString() -> "${kv.decodeDouble(dataKey)}"
            MMKVDataType.STRING.toString() -> kv.decodeString(dataKey)
            MMKVDataType.ENTITY.toString() -> kv.decodeString(dataKey)
            else -> ""
        }
    }
}