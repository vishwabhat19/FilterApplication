FROM openjdk:8
ADD target/product-filter.jar product-filter.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "product-filter.jar"]