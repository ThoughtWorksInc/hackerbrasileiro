#!/bin/bash

echo "Updating image rotation (setting it to be 90 degrees)..."

for image in $(find $HACKERBRASILEIRO_PHOTO_PATH -iname '*.jpg' -type f) ; do
  image_orientation=`identify -verbose $image | grep exif:Orientation`

  if [ "$image_orientation" != "" ] && [ "$image_orientation" != "exif:Orientation: 1" ] ; then
    echo "Current image orientation is: $image_orientation"
    echo "Updating it to be TopLeft (rotating it 90 degrees)..."
    jpegtran -rotate 90 $image > $image.tmp
    rm $image
    mv $image.tmp $image
    echo "Image '$image' updated!"
  fi
done

echo "Finished updating image rotation!"
