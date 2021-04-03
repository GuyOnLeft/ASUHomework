#include <stdio.h>
#include <stdlib.h>
#include "bmp.h"
#include "pixel.h"
#include <assert.h>

int main(int argc, char* argv[])
{

    FILE* x6_file = fopen(argv[1], "rb"); //open binary file for reading...
    FILE* my_x6_file = fopen("output.bmp", "wb"); //open binary file for writing..
    FILE* cropped_file = fopen("my_crop.bmp", "wb"); //create a file to write cropped image
    FILE* expected_file = fopen("expected.txt", "w");
    char* expected_output = "1";
    fprintf(expected_file, "%s", expected_output);
    fclose(expected_file);


    char* error = NULL;

    BMPImage* x6_image = read_bmp(x6_file, &error);
    if(error != NULL)
    {
        printf("%s", error);
        fclose(x6_file);
        fclose(my_x6_file);
        fclose(cropped_file);
        free(x6_image);
        free(error);
        return EXIT_FAILURE;
    }
    bool write_success = write_bmp(my_x6_file, x6_image, &error);
    if(error != NULL)
    {
        printf("%s", error);
        fclose(x6_file);
        fclose(my_x6_file);
        fclose(cropped_file);
        free_bmp(x6_image);
        free(error);
        return EXIT_FAILURE;
    }
    printf("%d", write_success);


    BMPImage* crop_image = crop_bmp(x6_image, 300,200,200,200,&error);
    if(error != NULL)
    {
        printf("%s", error);
        fclose(x6_file);
        fclose(my_x6_file);
        fclose(cropped_file);
        free_bmp(x6_image);
        free(error);
        return EXIT_FAILURE;
    }
    write_bmp(cropped_file, crop_image, &error);
    if(error != NULL)
    {
        printf("%s", error);
        fclose(x6_file);
        fclose(my_x6_file);
        fclose(cropped_file);
        free_bmp(x6_image);
        free_bmp(crop_image);
        free(error);
        return EXIT_FAILURE;
    }

    free_bmp(x6_image);
    free_bmp(crop_image);
    fclose(x6_file);
    fclose(my_x6_file);
    fclose(cropped_file);



    return EXIT_SUCCESS;
}