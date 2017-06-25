FROM openjdk:8
MAINTAINER Leonard Daume <lenny@reinvent.software>

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# Install curl, zip and unzip
RUN apt-get update -y && \
    apt-get install -y \
    curl \
    zip \
    unzip \
    wget \
    xvfb \
    git \
    apt-transport-https \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list && \
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 && \
    apt-get update -y && apt-get install -y sbt

RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list' && \
    apt-get update -y && apt-get install -y google-chrome-unstable

WORKDIR /app

COPY docker/docker-entrypoint.sh /
COPY . /app

RUN ls -al /

# ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["sbt","clean","test"]
