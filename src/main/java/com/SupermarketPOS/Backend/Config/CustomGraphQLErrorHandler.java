package com.SupermarketPOS.Backend.Config;
        import com.SupermarketPOS.Backend.Config.security.Exception.UserNotFoundException;
        import graphql.GraphQLError;
        import graphql.servlet.GenericGraphQLError;
        import graphql.servlet.GraphQLErrorHandler;
        import org.springframework.stereotype.Component;

        import java.util.List;

@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler {
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        for (GraphQLError error : errors) {
            if (error instanceof UserNotFoundException) {
                UserNotFoundException userNotFoundException = (UserNotFoundException) error;
                // Convert UserNotFoundException into a GraphQL error with a custom message
                return List.of(new GenericGraphQLError("User not found: " + userNotFoundException.getMessage()));
            }
//            else if (error instanceof AnotherCustomException) {
//                AnotherCustomException customException = (AnotherCustomException) error;
//                // Handle AnotherCustomException differently
//                return List.of(new GenericGraphQLError("Custom error: " + customException.getMessage()));
//            }
        }
        return errors;
    }

}
