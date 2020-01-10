package com.guofei.wu.springannotation.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:46
 * @since v3.0
 */
public class MyTypeFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {


        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();

        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println("----->" + className);


        String[] memberClassNames = classMetadata.getMemberClassNames();
        for (String memberClassName : memberClassNames) {
            System.out.println("memberClassName" + memberClassName);
        }

        if (className.contains("er")) {
            return true;
        }

        return false;
    }
}
