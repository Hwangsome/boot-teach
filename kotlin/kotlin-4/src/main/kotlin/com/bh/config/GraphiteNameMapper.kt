//package com.bh.config
//
//import io.micrometer.core.instrument.Meter
//import io.micrometer.core.instrument.config.NamingConvention
//import io.micrometer.graphite.GraphiteHierarchicalNameMapper
//
//class GraphiteNameMapper(vararg tagsAsprefix:String) :GraphiteHierarchicalNameMapper(*tagsAsprefix){
//    override fun toHierarchicalName(id: Meter.Id, convention: NamingConvention): String {
//       return super.toHierarchicalName(id, convention).replace(Regex("\\s+"),"_");
//    }
//
//}