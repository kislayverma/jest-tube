SRC_DIR=$1
TARGET_DIR=$2
echo "Copying from OPS_DIR Located at $SRC_DIR"
echo "Copying to   APP_DIR Located at $TARGET_DIR"
cp -a $SRC_DIR/.  $TARGET_DIR/router-tomcat/conf/
cp -a $SRC_DIR/setenv.sh $TARGET_DIR/router-tomcat/bin/
chmod -R +x $TARGET_DIR/bin
mkdir -p $TARGET_DIR/router-tomcat/logs
mkdir -p $TARGET_DIR/router-tomcat/temp
mkdir -p $TARGET_DIR/router-tomcat/lib
